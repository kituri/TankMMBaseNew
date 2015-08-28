package com.kituri.tankmmdatabase.ui.common;


/**
/**
 * Created by guanzhe on 14-6-4.
 */
public abstract class BaseActivity extends BaseFragmentActivity {
//
//    protected TextView titleTv,rightTv,leftTv;
//    protected ImageView leftIv,rightIv;
//    protected AnimateFirstDisplayListener mAnimateFirstDisplayListener;//imageLoader的监听事件，用来监听第一次加载图片
//    protected int mLayoutId = 0;// 布局Id
//    protected boolean mHasTitle = true;// 是否有Title Bar
//    protected View rootView;
//    protected ImageView noDataIv;
//    protected TextView noDataTv;
//    protected Button noDataBtn;
//    protected LinearLayout noDataLy;
//
//
    /**
     * 构造方法，默认为有公用title
     *
     * @param resId
     *            布局文件id
     */
    public BaseActivity(int resId) {
    	super(resId);
    }

    /**
     * 构造方法，手动设置有无公用title
     *
     * @param resId
     *            布局文件id
     * @param hasTitle
     *            是否有公用title
     */
    public BaseActivity(int resId, boolean hasTitle) {
    	super(resId, hasTitle);
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(mLayoutId);
//        rootView = findViewById(mLayoutId);
//        getData();//这个要放在前面
//        initTitle();
//        initView();
//    }
//
//    /**
//     * 获取从上个页面传递过来的数据，或者需要从本地读取的数据，如用户数据。
//     */
//    protected abstract void getData();
//
//    /**
//     * 初始化控件，并设置监听事件
//     */
//    protected abstract void initView();
//
//
//    /**
//     * 初始化标题栏上的左右按钮以及标题Text，同时添加左按钮的关闭事件
//     */
//    protected void initTitle() {
//        if (!mHasTitle) {
//            return;
//        }
//        mAnimateFirstDisplayListener = new AnimateFirstDisplayListener();
//        leftIv = (ImageView) this.findViewById(R.id.iv_left);
//        leftIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAnimateFirstDisplayListener.displayedImages.clear();
//                finish();
//                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
//            }
//        });
//        rightIv = (ImageView) this.findViewById(R.id.iv_right);
//        leftTv = (TextView) this.findViewById(R.id.tv_left);
//        rightTv = (TextView) this.findViewById(R.id.tv_right);
//        titleTv = (TextView) this.findViewById(R.id.tv_title);
//        noDataTv = (TextView) this.findViewById(R.id.mt_common_no_data_tv);
//        noDataIv = (ImageView) this.findViewById(R.id.mt_common_no_data_iv);
//        noDataBtn = (Button) this.findViewById(R.id.mt_common_no_data_btn);
//        noDataLy = (LinearLayout) this.findViewById(R.id.mt_no_data_ly);
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
//    }
//
//    /**
//     * 隐藏输入法面板
//     */
//
//    public void hideKeyboard(View view) {
//        InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
//        if (imm != null && getCurrentFocus() != null) {
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
//
//    public void onResume() {
//        super.onResume();
//        //友盟统计
//        MobclickAgent.onResume(this);
//    }
//
//    public void onPause() {
//        super.onPause();
//        //友盟统计
//        MobclickAgent.onPause(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//
//    public void onSuccess(Objects o, int REQUEST_CODE){}
//
//    public void onFail(Throwable t, int errorNo, String strMsg,int REQUEST_CODE){}
}
