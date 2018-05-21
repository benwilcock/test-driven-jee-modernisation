import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.AccessTarget;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.runner.RunWith;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.core.domain.properties.HasType.Functions.GET_TYPE;
import static com.tngtech.archunit.lang.Priority.LOW;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.superbiz.servlet")
public class JavaxPersistenceTests {

    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_ID =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(Id.class));

    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_COLUMN =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(Column.class));

    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_GENERATED_VALUE =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(GeneratedValue.class));

    private static final DescribedPredicate<JavaFieldAccess> TARGET_IS_PERSISTENCE_UNIT =
            JavaAccess.Functions.Get.<JavaFieldAccess, AccessTarget.FieldAccessTarget>target()
                    .is(annotatedWith(PersistenceUnit.class));


    @ArchIgnore
    @ArchTest
    public static final ArchRule noPersistenceAtAll = ArchRuleDefinition.priority(LOW).noClasses()
            .should().accessClassesThat().resideInAPackage("javax.persistence..");

    @ArchTest
    public static final ArchRule noEntities = ArchRuleDefinition.priority(LOW).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.persistence\\.Entity")));

    /**
     * False Positive
     * @see org.superbiz.servlet.JpaBean
     */
    @ArchTest
    public static final ArchRule noIdFields = ArchRuleDefinition.priority(LOW)
            .noClasses().should().setFieldWhere(TARGET_IS_ID);

    @ArchTest
    public static final ArchRule noColumnFields = ArchRuleDefinition.priority(LOW)
            .noClasses().should().setFieldWhere(TARGET_IS_COLUMN);

    /**
     * False Positive
     * @see org.superbiz.servlet.JpaBean
     */
    @ArchTest
    public static final ArchRule noGeneratedValues = ArchRuleDefinition.priority(LOW)
            .noClasses().should().setFieldWhere(TARGET_IS_GENERATED_VALUE);


    /**
     * False Positive
     * @see org.superbiz.servlet.JpaServlet
     */
    @ArchTest
    public static final ArchRule noPersistenceUnits = ArchRuleDefinition.priority(LOW)
            .noClasses().should().setFieldWhere(TARGET_IS_PERSISTENCE_UNIT);


}
