package po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by xz on 2019/10/28.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class User {
    private String name;

    private List<Double> list;
}
