package fr.univ_smb.isc.m2.controllers.rest;

import fr.univ_smb.isc.m2.config.rest.ResourceNotFoundException;
import fr.univ_smb.isc.m2.domain.weapon.Weapon;
import fr.univ_smb.isc.m2.domain.weapon.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class RestWeaponController {

    private final WeaponService weaponService;

    @Autowired()
    public RestWeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }


    @RequestMapping(value = "/weapons", method = RequestMethod.GET)
    public List<Weapon> weapon() {
        return weaponService.all();
    }

    @RequestMapping(value = "/weapons/{id}", method = RequestMethod.GET)
    public Weapon weapon(@PathVariable String id) {

        int weaponId = parseInt(id);

        List<Weapon> collect = weaponService.all().stream().filter(u -> u.getId() == weaponId).collect(toList());

        if (collect.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return collect.get(0);

    }

    @RequestMapping(value = "/weapons/{id}/delete", method = RequestMethod.GET)
    public void delWeapon(HttpServletRequest request, @PathVariable String id) {
        if(request.isUserInRole("ADMIN")) {
            int weaponId = parseInt(id);
            weaponService.del(weaponId);
        }
    }

    @RequestMapping(value = "/validateWeapons/{id}", method = RequestMethod.GET)
    public void validateWeapon(HttpServletRequest request, @PathVariable String id) {
        if(request.isUserInRole("ADMIN")) {
            int weaponId = parseInt(id);
            List<Weapon> collect = weaponService.all().stream().filter(u -> u.getId() == weaponId).collect(toList());
            collect.get(0).setState("Validated");
        }
    }

    @RequestMapping(value = "/addWeapon", method = RequestMethod.GET)
    public void addWeapon(HttpServletRequest request,
                            @RequestParam String name, @RequestParam String type, @RequestParam String group,
                            @RequestParam String range, @RequestParam String mode, @RequestParam String damage,
                            @RequestParam String penetration, @RequestParam String autonomy, @RequestParam String reload,
                            @RequestParam String attributes, @RequestParam String weight, @RequestParam String owner) {

        if(request.isUserInRole("ADMIN")){
            weaponService.add(name, type, group, range, mode, damage, penetration, autonomy, reload,
                              attributes, weight, "Inquisition", "Template");
        } else if (request.isUserInRole("USER")){
            weaponService.add(name, type, group, range, mode, damage, penetration, autonomy, reload,
                              attributes, weight, owner, "Waiting validation");
        }

    }

    @RequestMapping(value = "/validateWeapons", method = RequestMethod.GET)
    public List<Weapon> validateWeapons(HttpServletRequest request) {

        if(request.isUserInRole("ADMIN")) {
            List<Weapon> collect = weaponService.all().stream().filter(u -> u.getState() == "Waiting validation").collect(toList());

            if (collect.isEmpty()) {
                throw new ResourceNotFoundException();
            }

            return collect;
        }else
            return arsenal();
    }

    @RequestMapping(value = "/arsenal", method = RequestMethod.GET)
    public List<Weapon> arsenal() {

        List<Weapon> collect = weaponService.all().stream().filter(u -> u.getState() == "Template").collect(toList());

        if (collect.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return collect;
    }

    @RequestMapping(value = "/myWeapons", method = RequestMethod.GET)
    public List<Weapon> myWeapons(HttpServletRequest request) {

        String ownerName = request.getUserPrincipal().getName();

        List<Weapon> collect = weaponService.all().stream().filter(u ->u.getOwner() == ownerName).collect(toList());

        return collect;
    }


}