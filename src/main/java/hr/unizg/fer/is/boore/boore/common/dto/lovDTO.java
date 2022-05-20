package hr.unizg.fer.is.boore.boore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class lovDTO {
    private Integer id;
    private String label;
    private String label2;

    public lovDTO(Integer id, String label) {
        this(id, label, "");
    }
}
