package com.krrt.vl;

public class Partner {

    private String Name;
    private String Ref;
    private int Resource;

    public Partner(String name, String ref, int resource) {
        Name = name;
        Ref = ref;
        Resource = resource;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String ref) {
        Ref = ref;
    }

    public int getResource() {
        return Resource;
    }

    public void setResource(int resource) {
        Resource = resource;
    }
}
