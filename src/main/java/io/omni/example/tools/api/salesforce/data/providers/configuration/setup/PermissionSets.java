package io.omni.example.tools.api.salesforce.data.providers.configuration.setup;

import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.Custom;
import org.apache.commons.lang3.reflect.FieldUtils;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public enum PermissionSets implements HasName {

    MEETING_ADMIN("OCE Meeting Admin", "Meeting_Admin"),
    MEETING_COMPLIANCE_STATEMENT_USER("Meeting Compliance Statement User", "MeetingComplianceStatementUser"),
    MEETING_USER("OCE Meeting User", "Meeting_User"),
    MEETING_USER_2("OCE Meeting User 2", "Meeting_User2"),
    OCE9_MEETINGS_USER("Meetings User", "OCE9MeetingUser"),
    INSIGHT_USER("Insight User", "InsightUser"),
    @Custom
    MEETING_USER_2_CLONE("Meeting_User2_Clone"),
    @Custom
    MEETINGS_DISABLE_VALIDATION_RULES("MeetingsDisableValidationRules"),
    LOG_A_CALL_USER("OCE LogACall User", "LACUser"),
    LOG_A_CALL_USER_CUSTOM_ENTITIES("OCE LogACall User - Custom Entities", "LACUserCCE"),
    LOG_A_CALL_USER_DISCUSSIONS("OCE LogACall User - Discussions", "LACUserDiscussions"),
    LOG_A_CALL_USER_DTP("OCE LogACall User - DTP", "LACUserDTP"),
    LOG_A_CALL_USER_EXPENSES("OCE LogACall User - Expenses", "LACUserExpenses"),
    LOG_A_CALL_USER_ITEMS("OCE LogACall User - Items", "LACUserItems"),
    LOG_A_CALL_USER_PRESENTATIONS("OCE LogACall User - Presentations", "LACUserPresentations"),
    LOG_A_CALL_USER_SAMPLE("OCE LogACall User - Samples", "LACUserSamples"),
    LOG_A_CALL_USER_SURVEYS("OCE LogACall User - Surveys", "LACUserSurveys"),
    ORDER_MANAGEMENT_2_USER("OCE Order Management 2 User", "OrderManagement2_User"),
    ORDER_MANAGEMENT_2_ADMIN("OCE Order Management 2 Admin", "OrderManagement2_Admin"),
    INQUIRY_CREATOR("Inquiry Creator", "InquiryCreator"),
    GENERIC_WORKFLOW_ADMIN("OCE Generic Workflow Admin", "GenericWorkflowAdmin"),
    INQUIRY_RESPONDER("Inquiry Responder", "InquiryResponder"),
    GENERIC_WORKFLOW_USER("OCE Generic Workflow User", "GenericWorkflowUser"),
    CLM_CONTENT_API("OCE CLM Content API", "CLMContentAPI"),
    CLM_USER_PERMISSIONS("OCE CLM User Permissions", "CLMUserPermissions"),
    KAM_ADMIN("OCE KAMAdmin", "KAMAdmin"),
    KAM_USER("OCE KAMUser", "KAMUser"),
    MANAGE_CUSTOM_CLM_PRESENTATION_ON_MOBILE("Manage Custom CLM Presentation On Mobile", "Manage_Custom_CLM_Presentation_On_Mobile"),
    SURVEY_ADMIN("OCE Surveys Admin", "SurveyAdmin"),
    SURVEY_USER("OCE Surveys User", "SurveyUser"),
    ADMIN_CONSOLE_ALL("OCE Admin Console All", "AdminConsoleAll"),
    @Custom
    CALL_WITHOUT_READ_ACCESS_FIELDS("Call_Without_Read_Access_Fields"),
    @Custom
    CALL_WITH_READ_ACCESS_FIELDS("Call_With_Read_Access_Fields"),
    SAMPLES_MANAGEMENT_USER("OCE Samples Management User", "Samples_Management_User"),
    SAMPLES_MANAGEMENT_ADMIN("OCE Samples Management Admin", "Samples_Management_Admin"),
    BUDGET_ADMIN("OCE Budget Admin", "Budget_Admin"),
    BUDGET_USER("OCE Budget User", "Budget_User"),
    @Custom
    CUSTOM_CLM_CONTENT_API("CustomCLMContentAPI"),
    @Custom
    ATF_BULK("ATF_Bulk"),
    @Custom
    ATP_BULK("ATP_Bulk"),
    FORM_TEMPLATE_USER("OCE Form Template User", "FormTemplateUser"),
    FORM_TEMPLATE_ADMINISTRATION("OCE Form Template Administration", "FormTemplateAdministration"),
    TIME_OFF_TERRITORY_ADMINISTRATION("OCE Time Off Territory Administration", "TimeOffTerritoryAdministration"),
    TIME_OFF_TERRITORY_USER("OCE Time Off Territory User", "TimeOffTerritoryUser"),
    FIELD_COACHING_ADMINISTRATION("OCE FieldCoaching_Administration", "FieldCoaching_Administration"),
    FIELD_COACHING_COACH("OCE FieldCoaching_Coach", "FieldCoaching_Coach"),
    FIELD_COACHING_SALES_REP("OCE FieldCoaching_SalesRep", "FieldCoaching_SalesRep");

    private final String label;
    private final String name;

    PermissionSets(String label, String name) {
        this.label = label;
        this.name = name;
    }

    PermissionSets(String name) {
        this(name, name);
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return this.name;
    }

    public String getMetadataName() {
        boolean isCustom = FieldUtils.getField(this.getClass(), this.name()).isAnnotationPresent(Custom.class);
        return isCustom ? name : OCE_PREFIX + name;
    }
}
