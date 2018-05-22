import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.junit.LocationProvider;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class MyLocationProvider implements LocationProvider {

    @Override
    public Set<Location> get(Class<?> testClass) {
        String sources = "target/classes/";
        Set<Location> locations = new HashSet<>();
        locations.add(Location.of(Paths.get(sources)));
        return locations;
    }
}
