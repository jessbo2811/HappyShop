package ci553.happyshop.utility;

/**
 * UIStyle is a centralized Java final class that holds all JavaFX UI-related style and size constants
 * used across all client views in the system.
 *
 * These values are grouped here rather than being hardcoded throughout the codebase:
 * - improves maintainability, ensures style consistency,
 * - avoids hardcoded values scattered across the codebase.
 *
 * Example usages:
 * - UIStyle.HistoryWinHeight for setting the height of the order history window
 * - UIStyle.labelStyle for applying consistent styling to labels
 *
 * Design rationale:
 * - Declared as a final class: prevents inheritance and misuse
 * - Private constructor: prevents instantiation (this is a static-only utility class)
 * - Holds only static constants: ensures minimal memory usage and clean syntax
 *
 *  Why a Record is NOT appropriate:
 * - Records are intended for immutable instance data (e.g., DTOs), not static constants
 * - This class has no record components — everything is static
 * - We're using this as a utility container, not a data model
 *
 *  Reminder:
 *  Just because a class has no behavior and only data does NOT mean it should be a record.
 *  If all members are static constants, use a final utility class like this one.
 */

public final class UIStyle {

    //private constructor prevents instantiation
    private UIStyle() {
        throw new UnsupportedOperationException("UIStyle is a utility class");
    }

    public static final int customerWinWidth = 610;
    public static final int customerWinHeight = 300;
    public static final int removeProNotifierWinWidth = customerWinWidth / 2 + 160;
    public static final int removeProNotifierWinHeight = 230;

    public static final int pickerWinWidth = 310;
    public static final int pickerWinHeight = 300;

    public static final int trackerWinWidth = 210;
    public static final int trackerWinHeight = 300;

    public static final int warehouseWinWidth = 630;
    public static final int warehouseWinHeight = 300;
    public static final int AlertSimWinWidth = 300;
    public static final int AlertSimWinHeight = 170;
    public static final int HistoryWinWidth = 300;
    public static final int HistoryWinHeight = 140;

    public static final int EmergencyExitWinWidth = 175;
    public static final int EmergencyExitWinHeight = 175;

    public static final String labelTitleStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 16px; -fx-text-fill: black;";

    public static final String labelStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 14px; " +
            "-fx-text-align: right;" +
            "-fx-text-fill: black; " +
            "-fx-background-color: #9787AF;";

    public static final String labelLowStockStyle =
            "-fx-font-size: 12px; -fx-text-fill: red;";

    public static final String comboBoxStyle = "-fx-font-weight: bold; " +
            "-fx-font-size: 14px;";

    public static final String buttonStyle = "-fx-font-size: 15";

    public static final String purpleBtnStyle = "-fx-font-size: 15" + 
            "-fx-border-color: #9787AF;" + "-fx-background-color: #DDD7F5;" +
            "-fx-text-fill: #9787AF;";

    public static final String rootStyle = "-fx-padding: 8px; ";

    public static final String emergencyExitStyle = "-fx-padding: 1px;" + 
            "-fx-background-color: #58A4B0;";

    public static final String orderHubStyle = "-fx-padding: 8px;" +
            "-fx-background-color: #9787AF;";

    public static final String btnStyle = "-fx-border: none;" +
            "-fx-background-color: #58A4B0;";
            
    public static final String rootStyleBlue = "-fx-padding: 8px; " +
            "-fx-background-color: lightblue";

    public static final String rootStyleGray = "-fx-padding: 8px; ";

    public static final String rootStyleWarehouse = "-fx-padding: 8px; ";

    public static final String processingWindowStyle = "-fx-padding: 8px; " ;

    public static final String rootVipCustomerStyle = "-fx-padding: 8px; " +
            "-fx-background-color: burlywood";

    public static final String spinnerArrowStyle = "-fx-font-size: 12px; -fx-padding: 0;";

    public static final String textFiledStyle = "-fx-font-size: 16" +
            "-fx-text-align: right;";
    public static final String smallTextFiledStyle = "-fx-font-size: 14";
    public static final String tinyTextFiledStyle = "-fx-font-size: 12";

    public static final String labelMulLineStyle = "-fx-font-size: 16px; ";

    public static final String labelPriceStyle = "-fx-font-size: 16px; " +
            "-fx-background-color: lightyellow";

    public static final String listViewStyle = "-fx-border-color: #ccc; " +
            "-fx-border-width: 1px; -fx-background-color: white; -fx-font-size: 14px;";

    public static final String manageStockChildStyle = 
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String manageStockChildStyle1 = "-fx-background-color: lightgray;" +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String blueBtnStyle = "-fx-background-color: #A3C8D1;" +
            "-fx-border-color: #58A4B0;" + "-fx-text-fill: black;";

    public static final String plusFillBtnStyle = "-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";

    public static final String redFillBtnStyle = "-fx-background-color: red; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String searchBtnStyle = "-fx-background-color: purple; " +
            "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;";

    public static final String grayFillBtnStyle = "-fx-background-color: gray; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String blueFillBtnStyle = "-fx-background-color: blue; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";
    public static final String alertBtnStyle = "-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;";

    public static final String alertTitleLabelStyle = "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: red; " + "-fx-background-color: lightblue;";

    public static final String alertContentTextAreaStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal;-fx-control-inner-background: lightyellow; -fx-text-fill: darkblue;";

    public static final String alertContentUserActionStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal; -fx-text-fill: green;";

    public static final String tooltipStyle = "-fx-background-color: lightyellow; -fx-text-fill: red;";

    //=====Login Settings=====

    public static final int loginMinWidth = 200;

    public static final int loginMinHeight = 400;


}
