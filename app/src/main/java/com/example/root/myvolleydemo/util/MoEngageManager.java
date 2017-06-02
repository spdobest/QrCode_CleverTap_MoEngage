package com.example.root.myvolleydemo.util;

public class MoEngageManager {
    /*private static MoEHelper moEHelper;

    public static MoEHelper getInstance(final Activity context){
        if(moEHelper==null){
            moEHelper = MoEHelper.getInstance(context);
        }

        moEHelper.onStart(context);

        return moEHelper;
    }

    public static MoEHelper getInstance(final Context context){
        if(moEHelper==null){
            moEHelper = MoEHelper.getInstance(context);
        }
        return moEHelper;
    }
// send login details
    public static void sendLoginEvent(Context mContext,String clientId,boolean isSuccessfull){
        PayloadBuilder loginBuilder = new PayloadBuilder();
        loginBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrBoolean(MoengageKeys.IS_SUCCESSFUL, isSuccessfull);
        MoEngageManager.getInstance(mContext).trackEvent("Login", loginBuilder.build());
    }
// send register event details
    public static void sendRegisterEvent(Context mContext,String name,String emailId,long mobileNumber,String dob,String clientId){
        PayloadBuilder registerBuilder = new PayloadBuilder();
        registerBuilder.putAttrString(MoengageKeys.NAME, name)
                .putAttrString(MoengageKeys.EMAIL, emailId)
                .putAttrLong(MoengageKeys.MOBILE_NUMBER, mobileNumber)
                .putAttrDate(MoengageKeys.DOB, new Date(dob))
                .putAttrString(MoengageKeys.CLIENT_ID, clientId);
        MoEngageManager.getInstance(mContext).trackEvent("Registration completed", registerBuilder.build());
    }
    // add expences events
    public static void sendAddExpencesEvent(Context mContext,String clientId, String emailId,long mobileNumber,String categoryName,long amount){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.EMAIL, clientId)
                .putAttrLong(MoengageKeys.MOBILE_NUMBER, mobileNumber)
                .putAttrString(MoengageKeys.CATEGORY_NAME, categoryName)
                .putAttrLong(MoengageKeys.AMOUNT, amount);

        MoEngageManager.getInstance(mContext).trackEvent("Add expense", payloadBuilder.build());
    }

    //Experience ARQ - Invest now
    public static void sendExperienceARQEnvestNowEvent(Activity mContext,String clientId, String emailId,long mobileNumber){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.EMAIL, emailId)
                .putAttrLong(MoengageKeys.MOBILE_NUMBER, mobileNumber);

        MoEngageManager.getInstance(mContext).trackEvent("Experience ARQ - Invest now", payloadBuilder.build());
    }
    //Buy/ Sell orders
    public static void sendBuyOrSellEvent(Context mContext,String productType, double sharePrice,String orderType,int quantity,String symbol,Date validity,String exchange_selected,String clientId){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.PRODUCT_TYPE, productType)
                .putAttrDouble(MoengageKeys.SHARE_PRICE, sharePrice)
                .putAttrString(MoengageKeys.ORDER_TYPE, orderType)
                .putAttrInt(MoengageKeys.QUANTITY, quantity)
                .putAttrString(MoengageKeys.SYMBOL, symbol)
                .putAttrDate(MoengageKeys.VALIDITY, validity)
                .putAttrString(MoengageKeys.EXCHANGE_SELECTED, exchange_selected)
                .putAttrString(MoengageKeys.CLIENT_ID, clientId);
        MoEngageManager.getInstance(mContext).trackEvent("Buy/ Sell orders", payloadBuilder.build());
    }
    //Builded investment plan successfully
    public static void sendBuildedInvestmentPlanEvent(Context mContext,int age,int investmentPlan,int experienceInInvesting,String priority,String portFolioPreference,double amountToBeInvested,String preferedEquityAllocationMethos){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrInt(MoengageKeys.AGE, age)
                .putAttrInt(MoengageKeys.SHARE_PRICE, investmentPlan)
                .putAttrInt(MoengageKeys.EXPERIENCE_IN_INVESTING, experienceInInvesting)
                .putAttrString(MoengageKeys.PRIORITY, priority)
                .putAttrString(MoengageKeys.PORTFOLIO_PREFERENCE, portFolioPreference)
                .putAttrDouble(MoengageKeys.AMOUNT_TO_INVESTED, amountToBeInvested)
                .putAttrString(MoengageKeys.PREFERED_EQUITY_ALLOCATION_METHOD, preferedEquityAllocationMethos);
        MoEngageManager.getInstance(mContext).trackEvent("Builded investment plan successfully", payloadBuilder.build());
    }

    // Fund Transfer
    public static void sendFundTransferEvent(Context mContext,String productType, double sharePrice,String orderType,int quantity,String symbol,Date validity,String exchange_selected,String clientId){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.PRODUCT_TYPE, productType)
                .putAttrDouble(MoengageKeys.SHARE_PRICE, sharePrice)
                .putAttrString(MoengageKeys.ORDER_TYPE, orderType)
                .putAttrInt(MoengageKeys.QUANTITY, quantity)
                .putAttrString(MoengageKeys.SYMBOL, symbol)
                .putAttrDate(MoengageKeys.VALIDITY, validity)
                .putAttrString(MoengageKeys.EXCHANGE_SELECTED, exchange_selected)
                .putAttrString(MoengageKeys.CLIENT_ID, clientId);
        MoEngageManager.getInstance(mContext).trackEvent("Fund Transfer", payloadBuilder.build());
    }

    // addGoal
    public static void sendAddGoalEvent(Context mContext,String clientId, String email,long mobileNumber ,String goalType,double currentValue,int achievedInYears){

        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.EMAIL, email)
                .putAttrLong(MoengageKeys.ORDER_TYPE, mobileNumber)
                .putAttrString(MoengageKeys.GOAL_TYPE, goalType)
                .putAttrDouble(MoengageKeys.CURRENT_VALUE, currentValue)
                .putAttrInt(MoengageKeys.ACHIEVE_IN_YEAR, achievedInYears);
        MoEngageManager.getInstance(mContext).trackEvent("addGoal", payloadBuilder.build());
    }

    // Calculate - Child education
    public static void sendCalculateChildEducationEvent(Context mContext,String clientId, String email,long mobileNumber ,double cost,int timeInMonths){

        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.EMAIL, email)
                .putAttrLong(MoengageKeys.ORDER_TYPE, mobileNumber)
                .putAttrDouble(MoengageKeys.COST, cost)
                .putAttrInt(MoengageKeys.TIME_IN_MONTHS, timeInMonths);
        MoEngageManager.getInstance(mContext).trackEvent("Calculate - Child education", payloadBuilder.build());
    }

    // Calculate - Home purchase
    public static void sendCalculateHomePurchasesEvent(Context mContext,String clientId, String email,long mobileNumber ,String goalType,double downPayment,int achievedInYears){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.EMAIL, email)
                .putAttrLong(MoengageKeys.MOBILE_NUMBER, mobileNumber)
                .putAttrString(MoengageKeys.GOAL_TYPE, goalType)
                .putAttrDouble(MoengageKeys.DOWN_PAYMENT, downPayment)
                .putAttrInt(MoengageKeys.ACHIEVE_IN_YEAR, achievedInYears);
        MoEngageManager.getInstance(mContext).trackEvent("Calculate - Home purchase", payloadBuilder.build());
    }

    // Profile
    public static void sendProfileEvent(Context mContext,String clientId,String name, String email,long mobileNumber ,Date dob,double invested,double monthlyIncome,double monthlyEMiHomeloan,double monthlyEmiEducationLoan,double monthlyEmiOthers){
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        payloadBuilder.putAttrString(MoengageKeys.CLIENT_ID, clientId)
                .putAttrString(MoengageKeys.NAME, name)
                .putAttrString(MoengageKeys.EMAIL, email)
                .putAttrLong(MoengageKeys.MOBILE_NUMBER, mobileNumber)
                .putAttrDate(MoengageKeys.DOB, dob)
                .putAttrDouble(MoengageKeys.INVESTED,invested)
                .putAttrDouble(MoengageKeys.MONTHLY_INCOME,monthlyIncome)
                .putAttrDouble(MoengageKeys.MONTHLY_EMI_HOMELOAN,monthlyEMiHomeloan)
                .putAttrDouble(MoengageKeys.MONTHLY_EMI_EDUCATIONLOAN, monthlyEmiEducationLoan)
                .putAttrDouble(MoengageKeys.MONTHLY_EMI_OTHER, monthlyEmiOthers);
        MoEngageManager.getInstance(mContext).trackEvent("Profile", payloadBuilder.build());
    }

    interface MoengageKeys{
        // LOGIN
        public static final String CLIENT_ID = "Client ID";
        String IS_SUCCESSFUL = "Successful";
        //REGISTRATION
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String MOBILE_NUMBER = "Mobile Number";
        public static final String DOB = "DOB";

        // ADD EXPENCES
        public static final String EXPENSE_TYPE = "Expense Type";
        public static final String CATEGORY_NAME = "Category Name";
        public static final String AMOUNT = "Amount";

        // Buy Or Sell orders
        public static final String PRODUCT_TYPE = "Product Type";
        public static final String SHARE_PRICE = "Share Price";
        public static final String ORDER_TYPE = "Order Type";
        public static final String QUANTITY = "Quantity";
        public static final String VALIDITY = "Validity";
        public static final String EXCHANGE_SELECTED = "Exchange Selected";
        public static final String SYMBOL = "Symbol";
        public static final String GOAL_TYPE = "Goal Type";

        // Builded investment plan successfully
        public static final String AGE = "Age";
        public static final String INVESTMENT_PLAN = "Investment plan";
        public static final String EXPERIENCE_IN_INVESTING = "Experience in investing";
        public static final String PRIORITY = "Priority";
        public static final String PORTFOLIO_PREFERENCE = "portfolio preferences";
        public static final String AMOUNT_TO_INVESTED = "Amount to be invested";
        public static final String PREFERED_EQUITY_ALLOCATION_METHOD = "Preferred Equity Allocation method";

        // FUND TRANSFER
       // product type,Share Price,Order Type, Quantity, Symbol, Validity, Exchange Selected, Client ID

        // Add Goal
        //Client ID, Email, Mobile Number, Goal Type
        public static final String  CURRENT_VALUE = "Current Value";
        public static final String  ACHIEVE_IN_YEAR = "Achieve in years";

        //Calculate - Child education
        // Client ID,  Email,   Mobile Number
        public static final String COST = "Cost";
        public static final String TIME_IN_MONTHS =  "Time in months";

        //Calculate - Home purchase
        // Client ID, Email, Mobile Number
        public static final String DOWN_PAYMENT =  "Down Payment";
        //Achieve in years

        public static final String INVESTED = "invested";
        public static final String MONTHLY_INCOME = "monthlyIncome";
        public static final String MONTHLY_EMI_HOMELOAN = "monthlyEmiHomeLoan";
        public static final String MONTHLY_EMI_EDUCATIONLOAN= "monthlyEmiEeducationLoan";
        public static final String MONTHLY_EMI_OTHER = "monthlyEmiOther";
    }*/
}
