package xyz.energytrading.demo.dto;

import org.jetbrains.annotations.NotNull;

public class ImportantInfoDTO {

    @NotNull
    public Long id;
    public String name;

    public ImportantInfoDTO(@NotNull Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
