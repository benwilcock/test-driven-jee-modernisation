import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.AccessTarget;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.core.domain.properties.HasType.Functions.GET_TYPE;
import static com.tngtech.archunit.lang.Priority.HIGH;
import static com.tngtech.archunit.lang.Priority.LOW;
import static com.tngtech.archunit.lang.Priority.MEDIUM;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.superbiz.servlet")
public class JavaxEjbTests {

    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_EJB =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(EJB.class));

    /**
     * False Positive. @EJB is set to Retention.RUNTIME, so should be OK?
     * @see org.superbiz.servlet.RunAsServlet
     * */
    @ArchTest
    public static final ArchRule noEjbAnnotatedFields = ArchRuleDefinition.priority(LOW)
            .noClasses().should().setFieldWhere(TARGET_IS_EJB);

    @ArchTest
    public static final ArchRule noLocal = ArchRuleDefinition.priority(MEDIUM).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.LocalBean")));

    @ArchTest
    public static final ArchRule noEjbExceptions = ArchRuleDefinition.priority(MEDIUM).noClasses()
            .should().accessClassesThat().areAssignableTo(EJBException.class)
            .orShould().accessClassesThat().areAssignableTo(EJBAccessException.class);

    @ArchTest
    public static final ArchRule noStateless = ArchRuleDefinition.priority(MEDIUM).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.Stateless")));

    @ArchTest
    public static final ArchRule noStateful = ArchRuleDefinition.priority(HIGH).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.Stateful")));

    @ArchTest
    public static final ArchRule noMessageDriven = ArchRuleDefinition.priority(HIGH).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.MessageDriven")));

    @ArchTest
    public static final ArchRule noRemote = ArchRuleDefinition.priority(HIGH).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.Remote")));


}
