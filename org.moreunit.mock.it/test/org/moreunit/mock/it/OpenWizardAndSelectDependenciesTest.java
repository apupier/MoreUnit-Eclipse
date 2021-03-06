package org.moreunit.mock.it;

import org.eclipse.jdt.ui.JavaUI;
import org.junit.Test;
import org.moreunit.mock.UiTestCase;
import org.moreunit.mock.actions.MockDependenciesAction;
import org.moreunit.test.context.Context;
import org.moreunit.test.context.Preferences;
import org.moreunit.test.context.TestType;

@Context(mainSrc = "SomeConcept.cut.java.txt",
        testSrc = "SomeConcept.test.java.txt",
        preferences = @Preferences(testType = TestType.JUNIT4,
                testClassNameTemplate = "${srcFile}(Test|TestWithParent)"))
public class OpenWizardAndSelectDependenciesTest extends UiTestCase
{
    private final String expectationQualifier = "Mockito_post_1.9";
    private final String templateId = "org.moreunit.mock.mockitoWithAnnotationsAndJUnitRunner1.9";

    private MockDependenciesAction mockDependenciesAction = new MockDependenciesAction();

    @Test
    public void should_mock_all_dependencies_from_class_under_test() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConcept"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn(expectationQualifier + "_all_dependencies.expected.java.txt");
    }

    @Test
    public void should_mock_all_dependencies_from_test_class() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTest"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn(expectationQualifier + "_all_dependencies.expected.java.txt");
    }

    @Test
    public void should_also_mock_injected_dependencies() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .showInjectableFields()
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTest"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn(expectationQualifier + "_with_injectable_dependencies.expected.java.txt");
    }

    @Test
    public void should_allow_for_mocking_all_fields() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .showAllFields()
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTest"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn(expectationQualifier + "_with_all_fields.expected.java.txt");
    }

    @Test
    public void should_mock_some_dependencies() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkElements("SomeConcept", "setSomeListOfThings", "runnable")
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConcept"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn(expectationQualifier + "_some_dependencies.expected.java.txt");
    }

    @Test
    @Context(mainSrc = "SomeConcept.cut.java.txt",
            testSrc = "SomeConcept.test_with_parent.test.java.txt",
            preferences = @Preferences(testType = TestType.JUNIT4,
                    testClassNameTemplate = "${srcFile}TestWithParent"))
    public void should_place_type_annotation_at_the_right_place_when_test_case_has_parent_class() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTestWithParent"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTestWithParent").hasSameSourceAsIn(expectationQualifier + "_test_with_parent.expected.java.txt");
    }

    @Test
    @Context(mainSrc = "SomeConcept.cut.java.txt",
            testSrc = "SomeConcept.test_with_comment.test.java.txt",
            preferences = @Preferences(testType = TestType.JUNIT4,
                    testClassNameTemplate = "${srcFile}TestWithComment"))
    public void should_place_type_annotation_at_the_right_place_when_test_case_has_comment() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTestWithComment"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTestWithComment").hasSameSourceAsIn(expectationQualifier + "_test_with_comment.expected.java.txt");
    }

    @Test
    @Context(mainSrc = "dependencies_with_complex_generic_types.cut.java.txt",
            testSrc = "dependencies_with_complex_generic_types.test.java.txt",
            preferences = @Preferences(testType = TestType.JUNIT4,
                    testClassNameTemplate = "${srcFile}Test"))
    public void should_also_mock_dependencies_with_complex_generic_types() throws Exception
    {
        // given
        wizardDriver.whenMockDependenciesPageIsOpen()
                .selectTemplate(templateId)
                .checkAllElements()
                .done();

        // when
        JavaUI.openInEditor(context.getCompilationUnit("te.st.SomeConceptTest"));
        mockDependenciesAction.execute(null);

        // then
        context.assertCompilationUnit("te.st.SomeConceptTest").hasSameSourceAsIn("dependencies_with_complex_generic_types.expected.java.txt");
    }
}
