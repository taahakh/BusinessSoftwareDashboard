public enum Identifier {
    // This is not the employee hierarchy exactly.
    // This is about who can access the KPI and users of the business
    // You can customise employee heirarchy but these enums set which user has certain rights to parts of the program

    // Users, permissions and KPI
    ADMIN, // delete business, change business name, remove users
    USER, // add users
    ROLE, // assign kpis for each role
    // Viewing and Editing KPI
    EDITOR, // can edit and update values
    VIEWER // view essential information
}
