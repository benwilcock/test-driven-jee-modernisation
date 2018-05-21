import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.AccessTarget;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.superbiz.servlet")
public class JavaxAnnotationTests {


    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_RESOURCE =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(Resource.class));


    @ArchTest
    public static final ArchRule noResourceAnnotations = noClasses().should().setFieldWhere(TARGET_IS_RESOURCE);

//    @ArchTest
//    public static final ArchRule noNaming =
}
