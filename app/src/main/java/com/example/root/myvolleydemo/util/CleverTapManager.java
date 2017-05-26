package com.example.root.myvolleydemo.util;

import android.content.Context;
import android.text.TextUtils;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;

import java.util.HashMap;

/**
 * Created by root on 5/24/17.
 */

public class CleverTapManager {

    private static CleverTapAPI cleverTapAPI = null;

    public static CleverTapAPI getInstance(Context context) {

        if (cleverTapAPI == null) {

            try {
                // CleverTap
                CleverTapAPI.setDebugLevel(1); // optional
                cleverTapAPI = CleverTapAPI.getInstance(context);
            } catch (CleverTapMetaDataNotFoundException e) {
                // handle appropriately
                e.printStackTrace();
            } catch (CleverTapPermissionsNotSatisfied e) {
                // handle appropriately
                e.printStackTrace();
            }
            assert cleverTapAPI != null;
        }
        return cleverTapAPI;
    }

    public static void sendLoginEvents(Context context, String clientId, boolean isSuccessful) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        if (isSuccessful)
            prodViewedAction.put(CleverTapEvents.IS_SUCCESS, "YES");
        else
            prodViewedAction.put(CleverTapEvents.IS_SUCCESS, "NO");

        CleverTapManager.getInstance(context).event.push("Login", prodViewedAction);
    }

    public static void sendLoginFailedEvents(Context context, String clientId, String errorMessage) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.ERROR, errorMessage);
        CleverTapManager.getInstance(context).event.push("LoginFailed", prodViewedAction);
    }

    public static void sendRegisterEvents(Context context, String name, String clientId, boolean isSuccessful) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(clientId)) {
            prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        }
        if (!TextUtils.isEmpty(name)) {
            prodViewedAction.put(CleverTapEvents.NAME, name);
        }
        if (isSuccessful)
            prodViewedAction.put(CleverTapEvents.IS_SUCCESS, "YES");
        else
            prodViewedAction.put(CleverTapEvents.IS_SUCCESS, "NO");

        CleverTapManager.getInstance(context).event.push("Signup", prodViewedAction);
    }

    public static void sendRegisterFailedEvents(Context context, String name, String errorMessage) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.NAME, name);
        prodViewedAction.put(CleverTapEvents.ERROR, errorMessage);
        CleverTapManager.getInstance(context).event.push("SignupFailed", prodViewedAction);
    }

    public static void sendSetBudgetEvents(Context context, String clientId, double monthlyAmount) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.MONTHLY_AMOUNT, monthlyAmount);
        CleverTapManager.getInstance(context).event.push("Set Budget", prodViewedAction);
    }

    public static void sendButtonClickEvents(Context context, String clientId, String buttonName) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.BUTTON, buttonName);
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        CleverTapManager.getInstance(context).event.push("Clicked", prodViewedAction);
    }

    public static void sendScreenViewEvents(Context context, String clientId, String screenName, String subCategory) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SCREEN_NAME, screenName);
        if (!TextUtils.isEmpty(subCategory))
            prodViewedAction.put(CleverTapEvents.SCREEN_NAME, screenName);

        CleverTapManager.getInstance(context).event.push("Screen Viewed", prodViewedAction);
    }

    public static void sendIncomeAddedEvents(Context context, String clientId, double amount, String type) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.AMOUNT, amount);
        prodViewedAction.put(CleverTapEvents.TYPE, type); // monthly or one time

        CleverTapManager.getInstance(context).event.push("Income Added", prodViewedAction);
    }

    public static void sendExpenceAddedEvents(Context context, String clientId, double amount, String type) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.AMOUNT, amount);
        prodViewedAction.put(CleverTapEvents.TYPE, type); // monthly or one time

        CleverTapManager.getInstance(context).event.push("Expense Added", prodViewedAction);
    }

    public static void sendChilodEducationEvents(Context context, String clientId, double costOfEducation, int timeUntilGrand, double costOfPG, int timeUntilPG, String name, String dob, String educationTypeGoal) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.COST_OF_EDUCATION, costOfEducation);
        prodViewedAction.put(CleverTapEvents.TIME_UNTIL_GRAD, timeUntilGrand);
        prodViewedAction.put(CleverTapEvents.COST_OF_PG, costOfPG);
        prodViewedAction.put(CleverTapEvents.TIME_UNTIL_PG, timeUntilPG);
        prodViewedAction.put(CleverTapEvents.NAME, name);
        prodViewedAction.put(CleverTapEvents.DOB, dob);
        prodViewedAction.put(CleverTapEvents.EDUCATION_GOAL_TYPE, educationTypeGoal);

        CleverTapManager.getInstance(context).event.push("Child Edu Calc", prodViewedAction);
    }

    public static void sendHomeAffordabilityEvents(Context context, String clientId, double monthlyIncome, double existingEmi, float interestRate, double amountFinanced, int hoamLoanTerms, int emiSalaryRatio) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.EXISTING_EMI, existingEmi);
        prodViewedAction.put(CleverTapEvents.INTEREST_RATE, interestRate);
        prodViewedAction.put(CleverTapEvents.AMOUNT_FINANCED, amountFinanced);
        prodViewedAction.put(CleverTapEvents.HOAM_LOAN_TERM, hoamLoanTerms);
        prodViewedAction.put(CleverTapEvents.EMI_SALARY_RATIO, emiSalaryRatio);

        CleverTapManager.getInstance(context).event.push("Home Affodability Calc", prodViewedAction);
    }

    public static void sendGoalSetEvents(Context context, String clientId, String type, String source, double value, int targetYear, String recurringPayout) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.TYPE, type);
        prodViewedAction.put(CleverTapEvents.SOURCE, source);
        prodViewedAction.put(CleverTapEvents.VALUE, value);
        prodViewedAction.put(CleverTapEvents.TARGET_YEAR, targetYear);
        prodViewedAction.put(CleverTapEvents.RECURRING_PAYOUT, recurringPayout);

        CleverTapManager.getInstance(context).event.push("Goal Set", prodViewedAction);
    }

    public static void sendSearchEvents(Context context, String clientId, String selectedCrip, String selectedExchange) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, selectedCrip);
        prodViewedAction.put(CleverTapEvents.SELECTED_EXCHANGE, selectedExchange);

        CleverTapManager.getInstance(context).event.push("Search", prodViewedAction);
    }

    public static void sendBuyEvents(Context context, String clientId, String selectedCrip, String selectedExchange, int quantity, double price, String productType, String orderType, String validity, String type, String source) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, selectedCrip);
        prodViewedAction.put(CleverTapEvents.SELECTED_EXCHANGE, selectedExchange);
        prodViewedAction.put(CleverTapEvents.QUANTITY, quantity);
        prodViewedAction.put(CleverTapEvents.PRICE, price);
        prodViewedAction.put(CleverTapEvents.PRODUCT_TYPE, productType);
        prodViewedAction.put(CleverTapEvents.ORDER_TYPE, orderType);
        prodViewedAction.put(CleverTapEvents.VALIDITY, validity);
        prodViewedAction.put(CleverTapEvents.TYPE, type);
        prodViewedAction.put(CleverTapEvents.SOURCE, source);

        CleverTapManager.getInstance(context).event.push("Buy", prodViewedAction);
    }

    public static void sendSellEvents(Context context, String clientId, String selectedCrip, String selectedExchange, int quantity, double price, String productType, String orderType, String validity, String type, String source, String sellType) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, selectedCrip);
        prodViewedAction.put(CleverTapEvents.SELECTED_EXCHANGE, selectedExchange);
        prodViewedAction.put(CleverTapEvents.QUANTITY, quantity);
        prodViewedAction.put(CleverTapEvents.PRICE, price);
        prodViewedAction.put(CleverTapEvents.PRODUCT_TYPE, productType);
        prodViewedAction.put(CleverTapEvents.ORDER_TYPE, orderType);
        prodViewedAction.put(CleverTapEvents.VALIDITY, validity);
        prodViewedAction.put(CleverTapEvents.TYPE, type);
        prodViewedAction.put(CleverTapEvents.SOURCE, source);
        prodViewedAction.put(CleverTapEvents.SELL_TYPE, sellType);

        CleverTapManager.getInstance(context).event.push("Sell", prodViewedAction);
    }

    public static void sendBuyConfirmationEvents(Context context, String clientId, String selectedCrip, String selectedExchange, int quantity, double price, String productType, String orderType, String validity, String type, String failureReason, String buySuccess, String buyFailure, String buyType) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, selectedCrip);
        prodViewedAction.put(CleverTapEvents.SELECTED_EXCHANGE, selectedExchange);
        prodViewedAction.put(CleverTapEvents.QUANTITY, quantity);
        prodViewedAction.put(CleverTapEvents.PRICE, price);
        prodViewedAction.put(CleverTapEvents.PRODUCT_TYPE, productType);
        prodViewedAction.put(CleverTapEvents.ORDER_TYPE, orderType);
        prodViewedAction.put(CleverTapEvents.VALIDITY, validity);
        prodViewedAction.put(CleverTapEvents.TYPE, type);
        prodViewedAction.put(CleverTapEvents.FAILURE_REASON, failureReason);
        prodViewedAction.put(CleverTapEvents.BUY_SUCCESS, buySuccess);
        prodViewedAction.put(CleverTapEvents.BUY_FAILURE, buyFailure);
        prodViewedAction.put(CleverTapEvents.BUY_TYPE, buyType);

        CleverTapManager.getInstance(context).event.push("Buy Confirmation", prodViewedAction);
    }

    public static void sendSellConfirmationEvents(Context context, String clientId, String selectedCrip, String selectedExchange, int quantity, double price, String productType, String orderType, String validity, String type, String failureReason, String sellSuccess, String sellFailure, String sellType) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, selectedCrip);
        prodViewedAction.put(CleverTapEvents.SELECTED_EXCHANGE, selectedExchange);
        prodViewedAction.put(CleverTapEvents.QUANTITY, quantity);
        prodViewedAction.put(CleverTapEvents.PRICE, price);
        prodViewedAction.put(CleverTapEvents.PRODUCT_TYPE, productType);
        prodViewedAction.put(CleverTapEvents.ORDER_TYPE, orderType);
        prodViewedAction.put(CleverTapEvents.VALIDITY, validity);
        prodViewedAction.put(CleverTapEvents.TYPE, type);
        prodViewedAction.put(CleverTapEvents.FAILURE_REASON, failureReason);
        prodViewedAction.put(CleverTapEvents.SELL_SUCCESS, sellSuccess);
        prodViewedAction.put(CleverTapEvents.SELL_FAILURE, sellFailure);
        prodViewedAction.put(CleverTapEvents.SELL_TYPE, sellType);

        CleverTapManager.getInstance(context).event.push("Sell Confirmation", prodViewedAction);
    }


    public static void sendFundTransferEvents(Context context, String clientId, String type) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.SELECTED_SCRIP, type);
        CleverTapManager.getInstance(context).event.push("Fund Transfer", prodViewedAction);
    }

    public static void sendExperienceProfilingEvents(Context context, String clientId, int age, String termPeriod, String spans, String priority, String portFolioType, double amount, String preferredEquitAllocation) {
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();

        prodViewedAction.put(CleverTapEvents.CLIENTID, clientId);
        prodViewedAction.put(CleverTapEvents.AGE, age);
        prodViewedAction.put(CleverTapEvents.TERM_PERIOD, termPeriod);
        prodViewedAction.put(CleverTapEvents.SPANS, spans);
        prodViewedAction.put(CleverTapEvents.PRIORITY, priority);
        prodViewedAction.put(CleverTapEvents.PORTFOLIO_TYPE, portFolioType);
        prodViewedAction.put(CleverTapEvents.AMOUNT, amount);
        prodViewedAction.put(CleverTapEvents.PREFERED_EQUITY_ALLOCATION, preferredEquitAllocation);

        CleverTapManager.getInstance(context).event.push("Experience Profiling", prodViewedAction);
    }

    public static void sendProfileEvent(Context context, String clientId, String name, String email, String mobileNumber, String dob, String investedType, double monthlyIncome, double homeLoanEMi, double educationLoanEMi, double otherLoanEmi) {
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put(CleverTapEvents.CLIENTID, clientId);
        profileUpdate.put(CleverTapEvents.NAME, name);                    // String or number
        profileUpdate.put(CleverTapEvents.EMAIL, email);
        profileUpdate.put(CleverTapEvents.PHONE, mobileNumber);
        profileUpdate.put(CleverTapEvents.DOB, dob);
        profileUpdate.put(CleverTapEvents.INVESTED, investedType);
        profileUpdate.put(CleverTapEvents.MONTHLY_INCOME, monthlyIncome);
        profileUpdate.put(CleverTapEvents.MONTHLY_EMI_HOME_LOAN, homeLoanEMi);
        profileUpdate.put(CleverTapEvents.MONTHLY_EMI_EDUCATION_LOAN, educationLoanEMi);
        profileUpdate.put(CleverTapEvents.MONTHLY_EMI_OTHERS, otherLoanEmi);

        CleverTapManager.getInstance(context).profile.push(profileUpdate);
    }


    public static void sendEnvestEvent(Context context, String clientId, double amount, String source) {
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put(CleverTapEvents.CLIENTID, clientId);
        profileUpdate.put(CleverTapEvents.AMOUNT, amount);                    // String or number
        profileUpdate.put(CleverTapEvents.SOURCE, source);

        CleverTapManager.getInstance(context).event.push("Invest", profileUpdate);

    }

    public static void sendAddExpenseEvent(Context context,String clientId,String email,String mobileNumber,String expenseType,String category,double amount) {
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put(CleverTapEvents.CLIENTID, clientId);
        profileUpdate.put(CleverTapEvents.EMAIL, email);                    // String or number
        profileUpdate.put(CleverTapEvents.EMAIL, mobileNumber);
        profileUpdate.put(CleverTapEvents.EXPENCE_TYPE, expenseType);
        profileUpdate.put(CleverTapEvents.CATEGORY_NAME, category);
        profileUpdate.put(CleverTapEvents.AMOUNT, amount);

        CleverTapManager.getInstance(context).event.push("Add expense", profileUpdate);

    }


    interface CleverTapEvents {

        public static final String BOY_CLICKED = "buy clicked";
        // PROFILE ATTRIBUTES
        public static final String CLIENTID = "clientId";
        public static final String IS_SUCCESS = "issuccess";
        public static final String ERROR = "errorMessage";
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String DOB = "DOB";
        public static final String PHONE = "Phone";
        public static final String INVESTED = "Invested";
        //        public static final String MONTHLY_INCOME = "Monthly Income";
        public static final String MONTHLY_EMI_HOME_LOAN = "Monthly EMI Home Loan";
        public static final String MONTHLY_EMI_EDUCATION_LOAN = "Monthly EMI Education Loan";
        public static final String MONTHLY_EMI_OTHERS = "Monthly EMI (Others)";
        public static final String IDENTITY = "Identity"; // pass client id here


        // LOGIN and Register
        public static final String SOURCE = "source"; // facebook/email
        // SET BUDGET
        public static final String MONTHLY_AMOUNT = "Monthly Amount";
        // button clicked
        public static final String BUTTON = "Button"; // values Help me save / Expenditure 7 days / Expenditure 15 days / Show bank balance
        public static String SCREEN_NAME = "name";
        public static String SUB_CATEGORY = "Sub-category";
        public static final String TYPE = "type";
        public static final String AMOUNT = "Amount";
        public static final String COST_OF_EDUCATION = "Cost of Education";
        public static final String TIME_UNTIL_GRAD = "Time Until Grad";
        public static final String COST_OF_PG = "Cost of PG";
        public static final String TIME_UNTIL_PG = "Time Until PG";
        public static final String EDUCATION_GOAL_TYPE = "Education Goal Type"; // Graduation / Post Graduation / Both
        // Home Affodability Calc
        public static final String MONTHLY_INCOME = "Monthly Income";
        public static final String EXISTING_EMI = "Existing EMI";
        public static final String INTEREST_RATE = "Interest Rate";
        public static final String AMOUNT_FINANCED = "Amount Financed";
        public static final String HOAM_LOAN_TERM = "Home Loan term";
        public static final String EMI_SALARY_RATIO = "EMI / Salary Ratio";


        // Goal Set
//        public static final String SOURCE = "source";
        public static final String VALUE = "Value";
        public static final String TARGET_YEAR = "Target Year";
        public static final String RECURRING_PAYOUT = "Recurring Payout";

        // search
        public static final String SELECTED_SCRIP = "Selected SCRIP";
        public static final String SELECTED_EXCHANGE = "Selected Exchange";

        // buy and sell
        public static final String QUANTITY = "Quantity";
        public static final String PRICE = "Price";
        public static final String PRODUCT_TYPE = "Product Type";
        public static final String ORDER_TYPE = "Order Type";
        public static final String VALIDITY = "Validity";

        // Buy Confirmation
        public static final String FAILURE_REASON = "Failure Reason";
        public static final String BUY_SUCCESS = "Buy Success";
        public static final String BUY_FAILURE = "Buy Failure";

        //        Sell Confirmation
        public static final String SELL_SUCCESS = "Sell Success";
        public static final String SELL_FAILURE = "Sell Failure";
        //Experience Profiling
        public static final String AGE = "Age";
        public static final String SPANS = "Spans";
        public static final String PRIORITY = "Priority";
        public static final String PROTECTION_TYPE = "Portfolio Type";
        public static final String PREFERED_EQUITY_ALLOCATION = "Prefered equity allocation ";

        //        Add expense
        public static final String EXPENCE_TYPE = "Expense Type";
        public static final String CATEGORY_NAME = "Category Name";
        String SELL_TYPE = "sellType";
        String BUY_TYPE = "BuyType";
        String TERM_PERIOD = "termPeriod";
        String PORTFOLIO_TYPE = "portfolioType";
    }
}
