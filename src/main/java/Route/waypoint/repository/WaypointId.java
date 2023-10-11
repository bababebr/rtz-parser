package route.waypoint.repository;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Getter
@Setter
@EqualsAndHashCode
public class WaypointId implements Serializable {

    private long routeId;
    private long id;
}
