import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;


@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages="org.superbiz.servlet")
public class JavaxAnnotationSecurityTests {

//import javax.annotation.Resource;
//import javax.annotation.security.DeclareRoles;
//import javax.annotation.security.DenyAll;
//import javax.annotation.security.RolesAllowed;

//    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_DENYALL;
//
//
//    @ArchTest
//    public static final ArchRule noResourceAnnotations = noClasses().should().setFieldWhere(TARGET_IS_DENYALL);
}
