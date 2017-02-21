package by.bsuir.aipos.model;

public class StudentGroupXML {

    private long id;
    private String name;

    public StudentGroupXML() {
    }

    public StudentGroupXML(String name) {
        this.name = name;
    }

    public StudentGroupXML(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentGroupXML{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
