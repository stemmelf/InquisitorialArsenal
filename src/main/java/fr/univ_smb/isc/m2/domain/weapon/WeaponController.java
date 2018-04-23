package fr.univ_smb.isc.m2.domain.weapon;

import fr.univ_smb.isc.m2.config.rest.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rep")
public class WeaponController {

    private final WeaponRepository weaponRepository;

    @Autowired()
    public WeaponController(WeaponRepository weaponRepository) {
        weaponRepository.save(new Weapon("Fusil laser", "Armes Laser", "Base", "100m", "C/3/-","1d10+3 E",
                "0", "60", "1 AC", "Fiable", "4kg", "Inquisition", "Template"));
        weaponRepository.save(new Weapon("Pistolet laser", "Armes Laser", "Poing", "30m", "C/-/-", "1d10+2 E",
                "0", "30", "1 AC", "Fiable", "1,5kg", "Inquisition", "Template"));
        weaponRepository.save(new Weapon("Bolter", "Armes à Bolts", "Base", "90m", "C/2/-","1d10+5 X",
                "4", "24", "1 AC", " - ", "7kg", "Inquisition", "Template"));
        weaponRepository.save(new Weapon("Lance-flammes", "Armes Lance-flammes", "Base",  "20m", "C/-/-", "1d10+4 E",
                "3", "3", "2 AC", "Lance-flammes", "6kg", "Inquisition", "Template"));
        weaponRepository.save(new Weapon("Grenade à Fragmentation", "Grenades", "jet", "BFx3", " - ","2d10 X",
                "0", " - ", " - ", "Zone d'effet(4)", "0.5kg", "Inquisition", "Template"));

        this.weaponRepository = weaponRepository;
    }


    @RequestMapping(value = "/weapons", method = RequestMethod.GET)
    public List<Weapon> weapon() { return weaponRepository.findAll();}

    @RequestMapping(value = "/weapons/{id}", method = RequestMethod.GET)
    public Weapon weapon(@PathVariable String id) {

        Weapon weapon = weaponRepository.findOne(Long.parseLong(id));

        if (weapon == null) {
            throw new ResourceNotFoundException();
        }

        return weapon;

    }

    @RequestMapping(value = "/weapons/{id}/delete", method = RequestMethod.GET)
    public void delWeapon(HttpServletRequest request, @PathVariable String id) {
        if(request.isUserInRole("ADMIN")) {
            weaponRepository.delete(Long.parseLong(id));
        }
    }

    @RequestMapping(value = "/validateWeapons/{id}", method = RequestMethod.GET)
    public void validateWeapon(HttpServletRequest request, @PathVariable String id) {
        if(request.isUserInRole("ADMIN")) {
            Weapon weapon = weaponRepository.findOne(Long.parseLong(id));
            weapon.setState("Validated");
            weaponRepository.saveAndFlush(weapon);
        }
    }

    @RequestMapping(value = "/addWeapon", method = RequestMethod.GET)
    public void addWeapon(HttpServletRequest request,
                          @RequestParam String name, @RequestParam String type, @RequestParam String group,
                          @RequestParam String range, @RequestParam String mode, @RequestParam String damage,
                          @RequestParam String penetration, @RequestParam String autonomy, @RequestParam String reload,
                          @RequestParam String attributes, @RequestParam String weight, @RequestParam String owner) {

        if(request.isUserInRole("ADMIN")){
            Weapon weapon = new Weapon(name, type, group, range, mode, damage, penetration, autonomy, reload,
                    attributes, weight, "Inquisition", "Template");
            weaponRepository.saveAndFlush(weapon);
        } else if (request.isUserInRole("USER")){
            Weapon weapon = new Weapon(name, type, group, range, mode, damage, penetration, autonomy, reload,
                    attributes, weight, owner, "Waiting validation");
            weaponRepository.saveAndFlush(weapon);
        }

    }

    @RequestMapping(value = "/validateWeapons", method = RequestMethod.GET)
    public List<Weapon> validateWeapons(HttpServletRequest request) {

        if(request.isUserInRole("ADMIN")) {
            List<Weapon> collect = weaponRepository.findByState("Waiting validation");

            if (collect.isEmpty()) {
                throw new ResourceNotFoundException();
            }

            return collect;
        }else
            return arsenal();
    }

    @RequestMapping(value = "/arsenal", method = RequestMethod.GET)
    public List<Weapon> arsenal() {
        List<Weapon> collect = weaponRepository.findByState("Template");

        if (collect.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return collect;
    }

    @RequestMapping(value = "/myWeapons", method = RequestMethod.GET)
    public List<Weapon> myWeapons(HttpServletRequest request) {

        String owner = request.getUserPrincipal().getName();

        List<Weapon> collect = weaponRepository.findByOwner(owner);

        return collect;
    }


}