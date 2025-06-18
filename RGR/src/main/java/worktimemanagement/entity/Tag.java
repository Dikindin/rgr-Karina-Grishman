package worktimemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag extends AbstractEntity {

    @NotBlank(message = "Название метки не может быть пустым")
    @Column(nullable = false, unique = true)
    private String name;

    // Чтобы избежать циклической сериализации при возврате объектов
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    // Получение и установка
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
