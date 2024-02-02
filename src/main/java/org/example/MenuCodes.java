package org.example;

public enum MenuCodes {
    EXIT("0"),
    CREATE_DEP("1"),
    UPDATE_DEP("2"),
    DELETE_DEP("3"),
    ASSIGN_EMP_TO_DEP("4"),
    REASSIGN_EMP_TO_DEP("5"),
    CREATE_PROJECT("6"),
    UPDATE_PROJECT("7"),
    DELETE_PROJECT("8"),
    ASSIGN_EMP_TO_PROJECT("9"),
    REASSIGN_EMP_TO_PROJECT("10"),
    ASSIGN_MANAGER_TO_DEP("11"),
    REASSIGN_MANAGER_TO_DEP("12"),
    VIEW_MANAGER_SUBORDINATES("13"),
    CHANGE_MANAGER_MANAGE_LEVEL("14"),
    CREATE_EMP("15"),
    UPDATE_EMP("16"),
    DELETE_EMP("17"),
    CREATE_MANAGER("18"),
    UPDATE_MANAGER("19"),
    DELETE_MANAGER("20");

    private final String value;

    MenuCodes(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

