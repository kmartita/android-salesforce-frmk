package io.omni.example.tools.api.salesforce.sobject;

import io.omni.example.tools.ManagePackageData;

import java.util.Arrays;

import static java.lang.String.format;

/**
 * Each object should have a label to avoid a method 'activateDbSchemaEntity' failure
 * Workbench: Info -> Standard & Custom Objects -> object -> Attributes -> label or name
 * On SF you can't see all Standard objects
 */
public enum SObject {

    /**
     * Custom objects
     */
    ADDRESS("Address", "Address__c"),
    ACCOUNT_ADDRESS("Account Address", "AccountAddress__c"),
    ACCOUNT_FILTER("Account Filter", "AccountFilter__c"),
    ACCOUNT_FILTER_CRITERIA("Account Filter Criteria", "AccountFilterCriteria__c"),
    ACCOUNT_FILTER_SOBJECT("Account Filter SObject", "AccountFilterSObject__c"),
    ACCOUNT_TERRITORY_FIELDS("Account Territory Fields", "AccountTerritoryFields__c"),
    ACCOUNT_TERRITORY_PRODUCT("Account Territory Product", "AccountTerritoryProduct__c"),
    CALL("Call", "Call__c"),
    CALL_DETAIL("Call Detail", "CallDetail__c"),
    CALL_SAMPLE("Call Sample", "CallSample__c"),
    CALL_SAMPLES_ORDER("Call Sample Order", "CallSampleOrder__c"),
    CALL_ITEM("Call Item", "CallItem__c"),
    CALL_TO_DO("Call To-Do", "CallToDo__c"),
    CALL_ACCOUNT_TACTIC("Call Account Tactic", "CallAccountTactic__c"),
    CALL_PRESENTATION("Call Presentation", "CallPresentation__c"),
    CALL_SHARE("Share: Call", "Call__Share"),
    INQUIRY("Inquiry", "Inquiry__c"),
    INQUIRY_ANSWER("Inquiry Answer", "InquiryAnswer__c"),
    INQUIRY_QUESTION("Inquiry Question", "InquiryQuestion__c"),
    INQUIRY_TOPIC("Inquiry Topic", "InquiryTopic__c"),
    MEETING("Meeting", "Meeting__c"),
    ORDER_DELIVERY_2("Order Delivery", "OrderDelivery2__c"),
    ORDER_LINE_ITEM_2("Order Line Item", "OrderLineItem2__c"),
    ORDER_DETAIL_2("Order Detail", "OrderDetail2__c"),
    AFFILIATION("Affiliation", "Affiliation__c"),
    AFFILIATION_CONFIG("Affiliations", "AffiliationConfig__c"),
    AFFILIATION_TABLE_WIDGET_CONFIG("Affiliations Table", "AffiliationTableWidgetConfig__c"),
    PRODUCT("Product", "Product__c"),
    TERRITORY_PRODUCT_ALIGNMENT_RULE("Territory Product Alignment Rule", "TerritoryProductAlignmentRule__c"),
    LOT("Lot", "Lot__c"),
    SAMPLE_LOT_ALLOCATION("Sample Lot Allocation", "SampleLotAllocation__c"),
    GENERAL_EVENT("General Event", "GeneralEvent__c"),
    GENERAL_EVENT_EMPLOYEE_ATTENDEE("General Event Employee Attendee", "GeneralEventEmployeeAttendee__c"),
    TOPIC_PRODUCT("Topic Product", "TopicProduct__c"),
    TOPIC("Topic", "Topic__c"),
    TOPIC_SHARE("Topic share", "Topic__Share"),
    TOPIC_MATERIAL("Topic Material", "TopicMaterial__c"),
    SPEAKER("Speaker", "Speaker__c"),
    MCM_JOURNEY("MCM Journey", "MCMJourney__c"),
    MEETING_EXPENSE("Meeting Expense", "MeetingExpense__c"),
    MEETING_EXPENSE_ALLOCATION("Meeting Expense Allocation", "MeetingExpenseAllocation__c"),
    MEETING_EXPENSE_BUDGET_ALLOCATION("Meeting Expense Budget Allocation", "MeetingExpenseBudgetAllocation__c"),
    MEETING_LOCATION("Meeting Location", "MeetingLocation__c"),
    MEETING_MEMBER("Meeting Member", "MeetingMember__c"),
    MEETING_PRODUCT("Meeting Product", "MeetingProduct__c"),
    MEETING_TOPIC("Meeting Topic", "MeetingTopic__c"),
    LOCATION("Location", "Location__c"),
    EXPENSE_TYPE("Expense Type", "ExpenseType__c"),
    CUSTOM_SCRIPT("Custom Script", "CustomScript__c"),
    TIME_OFF_TERRITORY("Time Off Territory", "TimeOffTerritory__c"),
    BUDGET("Budget", "Budget__c"),
    BUDGET_SHARE("Budget__Share"),
    BUDGET_PRODUCT("Budget Product", "BudgetProduct__c"),
    MEETING_BUDGET_2("Meeting Budget", "MeetingBudget2__c"),
    MEETING_BUDGET("Meeting Budget", "MeetingBudget__c"),
    MEETING_HISTORY("Meeting History", "MeetingHistory__c"),
    RULE_2("Rule", "Rule2__c"),
    ORDER_2("Order", "Order2__c"),
    ORDER_DISCOUNT_2("Order Discount", "OrderDiscount2__c"),
    PRICELIST_2("Pricelist", "Pricelist2__c"),
    PRICELIST_ITEM_2("Pricelist Item", "PricelistItem2__c"),
    PRICELIST_RULE("Pricelist Rule", "PricelistRule__c"),
    ACCOUNT_GROUP("Account Group", "AccountGroup__c"),
    ACCOUNT_GROUP_AFFILIATION("Account Group Affiliation", "AccountGroupAffiliation__c"),
    ORDERS_HISTORICAL_DATA("Orders Historical Data", "OrdersHistoricalData__c"),
    PRODUCT_COMPONENT("Product Component", "ProductComponent__c"),
    SEGMENTATION("Segmentation", "Segmentation__c"),
    SEGMENTATION_ITEM("Segmentation Item", "SegmentationItem__c"),
    ACCOUNT_SEGMENTATION("Account Segmentation", "AccountSegmentation__c"),
    ACCOUNT_PLAN("Account Plan", "AccountPlan__c"),
    ACCOUNT_PLAN_PRODUCT("Account Plan Product", "AccountPlanProduct__c"),
    ACCOUNT_PLAN_MEMBER_STAKEHOLDER("Account Plan Member Stakeholder", "AccountPlanMemberStakeholder__c"),
    ACCOUNT_OBJECTIVE("Account Objective", "AccountObjective__c"),
    ACCOUNT_TACTIC("Account Tactic", "AccountTactic__c"),
    ACCOUNT_OBJECTIVE_PRODUCT("Account Objective Product", "AccountObjectiveProduct__c"),
    PRESENTATION_SHARE("Share: Presentation", "Presentation__Share"),
    ACCOUNT_FILTER_SHARE("Share: Account Filter", "AccountFilter__Share"),
    SEQUENCE_SHARE("Share: Sequence", "Sequence__Share"),
    PRESENTATION("Presentation", "Presentation__c"),
    SEQUENCE("Sequence", "Sequence__c"),
    SEQUENCE_PRODUCT("Sequence Product", "SequenceProduct__c"),
    SEQUENCE_FILE("Sequence File", "SequenceFile__c"),
    CLICK_STREAM_METRIC("Click Stream Metric", "ClickStreamMetric__c"),
    PRODUCT_MESSAGE("Product Message", "ProductMessage__c"),
    PRESENTATION_SEQUENCE("Presentation Sequence", "PresentationSequence__c"),
    BUSINESS_OBJECTIVE("Business Objective", "BusinessObjective__c"),
    BUSINESS_TACTIC("Business Tactic", "BusinessTactic__c"),
    BUSINESS_TO_DO("Business ToDo", "BusinessToDo__c"),
    BUSINESS_OBJECTIVE_PRODUCT("Business Objective Product", "BusinessObjectiveProduct__c"),
    TACTIC("Tactic", "Tactic__c"),
    TACTIC_ACCOUNT_PLAN_STAKEHOLDER("Tactic Account Plan Stakeholder", "TacticAccountPlanStakeholder__c"),
    TO_DO("To-Do", "ToDo__c"),
    TO_DO_ACCOUNT_PLAN_STAKEHOLDER("ToDo Account Plan Stakeholder", "ToDoAccountPlanStakeholder__c"),
    STAKEHOLDER("Stakeholder", "AccountPlanStakeholder__c"),
    METADATA("Metadata", "Metadata__c"),
    SYNC_TRANSACTION("Sync Transaction", "SyncTransaction__c"),
    SYNC_STATISTICS("Sync Statistics", "SyncStatistics__c"),
    SYNC_TRANSACTION_ITEM("Sync Transaction Item", "SyncTransactionItem__c"),
    ACCOUNT_DYNAMIC_LIST_CONFIG("Account Dynamic List Configuration", "AccountDynamicListConfiguration__c"),
    CALENDAR_CONFIG("Planner", "CalendarConfig__c"),
    APPLICATION_SETTINGS("Application Settings", "ApplicationSettings__c"),
    MCM_JOURNEY_PRODUCT("MCM Journey Product", "MCMJourneyProduct__c"),
    MEETINGS_CONFIG("Meetings Config", "MeetingsConfig__c"),
    MEETINGS_ORG_LEVEL_CONFIG("Meetings Org Level Config", "MeetingsOrgLevelConfig__c"),
    MEETING_VALIDATION_SETTINGS("Meeting Validation Settings", "MeetingValidationSettings__c"),
    MEETING_WORK_FLOW_ACTION_CONFIGURATION("Workflow Path", "MeetingWorkFlowActionConfiguration__mdt"),
    MEETING_WORK_FLOW_CONFIGURATION("Workflow Path", "MeetingWorkFlowConfiguration__mdt"),
    MEETING_WORK_FLOW_NODE_CONFIGURATION("Workflow Path Node", "MeetingWorkFlowNodeConfiguration__mdt"),
    UI_SCHEMA("UI Schema", "UiSchema__mdt"),
    TOT_SETTINGS("TOT Settings", "TOTSettings__c"),
    INSIGHT("Insight", "Insight__c"),
    INSIGHT_SETTINGS("Insight Settings", "InsightSettings__c"),
    INSIGHT_TOPIC("Insight Topic", "InsightTopic__c"),
    VOTE("Vote", "Vote__c"),
    INSIGHT_PRODUCT("InsightProduct", "InsightProduct__c"),
    TIME_OFF_TERRITORY_SLOTS("Time Off Territory Slots", "TimeOffTerritorySlots__c"),
    TOT_RULE("TOT Rule", "TOTRule__mdt"),
    ACTION("Action", "Action__mdt"),
    CONTEXT("Context", "Context__mdt"),
    CONTEXT_TYPE("Context Type", "ContextType__mdt"),
    CONTEXT_CONDITION("Context Condition", "ContextCondition__mdt"),
    CONTEXT_ACTION("Context Action", "ContextAction__mdt"),
    ORDER_CONFIGURATION_2_SETTINGS("Order Configuration Settings", "OrderConfiguration2Settings__c"),
    MIRF_SETTINGS("MIRFSettings", "MIRFSettings__c"),
    CLM_TRAINING_MODE("CLMTrainingMode", "CLMTrainingMode__c"),
    LOG_A_CALL_SETTINGS("LogACallSettings", "LogACallSettings__c"),
    SURVEY_RESPONSE("Survey Response", "SurveyResponse__c"),
    CALL_DIALOGUE("Call Dialog", "CallDialogue__c"),
    TERRITORY_PRODUCT_MESSAGE_ALIGNMENT_RULE("Territory Product Message Alignment Rule", "TerritoryProductMessageAlignmentRule__c"),
    SURVEY_QUESTION_RESPONSE("Survey Question Response", "SurveyQuestionResponse__c"),
    NEXT_BEST_CUSTOMER("Next Best Customer", "NextBestCustomer__c"),
    ADA_LITE_SETTINGS("Ada", "AdaLiteSettings__c"),
    SEARCH_BEFORE_CREATE_CONFIG("Search Before Create Config", "SearchBeforeCreateConfig__c"),
    ACCOUNT_SEARCH_TYPE_SETTINGS("Account Search Type Settings", "AccountSearchTypeSettings__c"),
    ACCOUNT_FILTER_CONFIG("AccountFilterConfig__c"),
    ACCOUNT_LIST_ITEM("AccountListItem__c"),
    BEST_TIME_SETTINGS("BestTimeSettings__c"),
    ACCOUNT_FILTER_COLUMN("AccountFilterColumn__c"),
    TERRITORY_PRODUCT_EXCLUSION("Territory Product Exclusion", "TerritoryProductExclusion__c"),
    RATING("Rating", "Rating__c"),
    RATING_LAYOUT("Rating Layout", "RatingLayout__c"),
    RATING_LAYOUT_ITEM("Rating Layout Item", "RatingLayoutItem__c"),
    WORKFLOW_ACTION("WorkflowAction__c"),
    WORKFLOW_CONTEXT_ACTION("WorkflowContextAction__c"),
    WORKFLOW_CONTEXT("WorkflowContext__c"),
    WORKFLOW_CONTEXT_CONDITION("WorkflowContextCondition__c"),
    WORKFLOW_CONTEXT_TYPE("WorkflowContextType__c"),
    WORKFLOW_PATH("WorkflowPath__c"),
    WORKFLOW_PATH_NODE("WorkflowPathNode__c"),
    DOCUMENT_TEMPLATE("DocumentTemplate__c"),
    DOCUMENT("Document__c"),
    DOCUMENT_TEMPLATE_VERSION("DocumentTemplateVersion__c"),
    DOCUMENT_SETTINGS("DocumentSettings__c"),
    OBJECT_WORKFLOW_PATH_ASSOCIATION("ObjectWorkflowPathAssociation__c"),
    FIELD_COACHING_EVENT("FieldCoachingEvent__c"),
    FIELD_COACHING_FORM_TEMPLATE("FieldCoachingFormTemplate__c"),
    FIELD_COACHING_FORM_TEMPLATE_SHARE("FieldCoachingFormTemplate__Share"),
    FIELD_COACHING_EVENT_DATA("FieldCoachingEventData__c"),
    FIELD_COACHING_EVENT_SETTINGS("FieldCoachingEventSettings__c"),
    FIELD_COACHING_FORM_QUESTION("FieldCoachingFormQuestion__c"),
    FIELD_COACHING_QUESTION("FieldCoachingQuestion__c"),
    COMPLIANCE_STATEMENT("ComplianceStatement__c"),
    TRIGGER_PERMISSION("TriggerPermission__c"),
    MOBILE_APP_UPGRADE("MobileAppUpgrade__c"),

    /**
     * Standard objects
     */
    ACCOUNT("Account", "Account"),
    ACCOUNT_SHARE("Account Share", "AccountShare"),
    CONTACT("Contact", "Contact"),
    PROFILE("Profile", "Profile"),
    ORGANIZATION("Organization", "Organization"),
    STANDARD_TOPIC("Standard Topic", "Topic"),
    STANDARD_TOPIC_ASSIGNMENT("Standard TopicAssignment", "TopicAssignment"),
    USER("User", "User"),
    CONTENT_DOCUMENT("Content Document", "ContentDocument"),
    GROUP("Group", "Group"),
    TERRITORY2("Territory2", "Territory2"),
    GROUP_MEMBER("Group Member", "GroupMember"),
    CONTENT_DOCUMENT_LINK("Content Document Link", "ContentDocumentLink"),
    CONTENT_VERSION("Content Version", "ContentVersion"),
    FIELD_PERMISSIONS("Field Permissions", "FieldPermissions"),
    PERMISSION_SET_ASSIGNMENT("Permission Set Assignment", "PermissionSetAssignment"),
    PERMISSION_SET("Permission Set", "PermissionSet"),
    RECORD_TYPE("Record Type", "RecordType"),
    SURVEY("Survey", "Survey"),
    SURVEY_INVITATION("Survey Invitation", "SurveyInvitation"),
    SURVEY_QUESTION("Survey Question", "SurveyQuestion"),
    SURVEY_QUESTION_CHOICE("Survey Question Choice", "SurveyQuestionChoice"),
    SURVEY_PAGE("Survey Page", "SurveyPage"),
    SURVEY_VERSION("Survey Version", "SurveyVersion"),
    CUSTOM_ACTION("Custom Action", "CustomAction__mdt"),
    DB_SCHEMA("DbSchema", "DbSchema__mdt"),
    ENTITY_DEFINITION("Entity Definition", "EntityDefinition"),
    USER_TERRITORY_2_ASSOCIATION("User Territory Association", "UserTerritory2Association"),
    OBJECT_TERRITORY2_ASSOCIATION("Object Territory Association", "ObjectTerritory2Association"),
    APEX_CLASS("ApexClass"),
    PERSON_ACCOUNT("Person Account", "PersonAccount"),
    BUSINESS_HOURS("BusinessHours"),
    DATA_CHANGE_EVENT_SETTINGS("DataChangeEventSettings", "DataChangeEventSettings__mdt"),
    MEDICAL_INQUIRY_REQUEST("Medical Inquiry Request", "MedicalInquiryRequest__c"),
    DATED_CONVERSION_RATE("DatedConversionRate");

    private final String name;
    private final String label;

    SObject(String label, String name) {
        this.label = label;
        this.name = name;
    }

    SObject(String name) {
        this(name, name);
    }

    public static SObject getByName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown sObject=%s\n", name)));
    }

    public String getName() {
        if (this.name.contains("QIDC")) {
            return this.name;
        } else {
            return ManagePackageData.updateFieldName(this.name);
        }
    }

    public String getLabel() {
        return this.label;
    }

}
