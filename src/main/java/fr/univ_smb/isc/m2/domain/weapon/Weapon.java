package fr.univ_smb.isc.m2.domain.weapon;

import javax.persistence.*;

@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    @Column(name="groupz")
    private String group;
    private String range;
    private String mode;
    private String damage;
    private String penetration;
    private String autonomy;
    private String reload;
    private String attributes;
    private String weight;
    private String owner;
    private String state;

    public Weapon() {
    }

    public Weapon(String name, String type, String group, String range, String mode, String damage, String penetration,
                  String autonomy, String reload, String attributes, String weight, String owner, String state) {
        this.name = name;
        this.type = type;
        this.group = group;
        this.range = range;
        this.mode = mode;
        this.damage = damage;
        this.penetration = penetration;
        this.autonomy = autonomy;
        this.reload = reload;
        this.attributes = attributes;
        this.weight = weight;
        this.owner = owner;
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getPenetration() {
        return penetration;
    }

    public void setPenetration(String penetration) {
        this.penetration = penetration;
    }

    public String getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(String autonomy) {
        this.autonomy = autonomy;
    }

    public String getReload() {
        return reload;
    }

    public void setReload(String reload) {
        this.reload = reload;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
