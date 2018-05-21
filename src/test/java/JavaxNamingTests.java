import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.superbiz.servlet")
public class JavaxNamingTests {

    @ArchTest
    public static final ArchRule noinItialContexts = noClasses().should().accessClassesThat().haveFullyQualifiedName("javax.naming.InitialContext");

    @ArchTest
    public static final ArchRule noContexts = noClasses().should().accessClassesThat().haveFullyQualifiedName("javax.naming.Context");

    @ArchTest
    public static final ArchRule noNameClassPairs = noClasses().should().accessClassesThat().haveFullyQualifiedName("javax.naming.NameClassPair");

    @ArchIgnore
    @ArchTest
    public static final ArchRule noNaming = noClasses().should().accessClassesThat().resideInAPackage("javax.naming..");
}
