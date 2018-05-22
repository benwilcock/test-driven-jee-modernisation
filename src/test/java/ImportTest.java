import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.core.domain.properties.HasType.Functions.GET_TYPE;
import static com.tngtech.archunit.lang.Priority.MEDIUM;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(locations = MyLocationProvider.class, importOptions = {ImportOption.DontIncludeTests.class, ImportOption.DontIncludeJars.class})
public class ImportTest {

    @ArchTest
    public static final ArchRule noLocal = ArchRuleDefinition.priority(MEDIUM).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.LocalBean")));
}
