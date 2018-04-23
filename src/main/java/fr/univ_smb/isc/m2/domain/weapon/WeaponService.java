package fr.univ_smb.isc.m2.domain.weapon;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponService {

    private final ArrayList<Weapon> weapons;

    public WeaponService() {
        weapons = new ArrayList<>();
        weapons.add(new Weapon("Fusil laser", "Armes Laser", "Base", "100m", "C/3/-","1d10+3 E",
                    "0", "60", "1 AC", "Fiable", "4kg", "Inquisition", "Template"));
        weapons.add(new Weapon("Pistolet laser", "Armes Laser", "Poing", "30m", "C/-/-", "1d10+2 E",
                "0", "30", "1 AC", "Fiable", "1,5kg", "Inquisition", "Template"));
        weapons.add(new Weapon("Bolter", "Armes à Bolts", "Base", "90m", "C/2/-","1d10+5 X",
                "4", "24", "1 AC", " - ", "7kg", "Inquisition", "Template"));
        weapons.add(new Weapon("Lance-flammes", "Armes Lance-flammes", "Base",  "20m", "C/-/-", "1d10+4 E",
                "3", "3", "2 AC", "Lance-flammes", "6kg", "Inquisition", "Template"));
        weapons.add(new Weapon("Grenade à Fragmentation", "Grenades", "jet", "BFx3", " - ","2d10 X",
                "0", " - ", " - ", "Zone d'effet(4)", "0.5kg", "Inquisition", "Template"));


    }

    public List<Weapon> all() {
        return weapons;
    }

    public void add(String name, String type, String group, String range, String mode, String damage, String penetration,
                            String autonomy, String reload, String attributes, String weight, String owner, String state)
    {
        weapons.add(new Weapon(name, type, group, range, mode, damage, penetration, autonomy, reload, attributes, weight, owner, state));
    }

    public void del(int id)
    {
        for (Weapon w : weapons) {
            if(w.getId() == id)
                weapons.remove(w);
        }
    }
}
