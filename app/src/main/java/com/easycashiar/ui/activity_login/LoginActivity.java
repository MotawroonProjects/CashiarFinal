package com.easycashiar.ui.activity_login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.easycashiar.R;
import com.easycashiar.adapters.CountriesAdapter;
import com.easycashiar.databinding.ActivityLoginBinding;
import com.easycashiar.databinding.DialogCountriesBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.CountryModel;
import com.easycashiar.models.LoginModel;
import com.easycashiar.mvp.activity_login_presenter.ActivityLoginPresenter;
import com.easycashiar.mvp.activity_login_presenter.ActivityLoginView;
import com.easycashiar.ui.activity_confirm_code.ConfirmCodeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity implements ActivityLoginView {
    private ActivityLoginBinding binding;
    private LoginModel model;
    private ActivityLoginPresenter presenter;
    private CountryModel[] countries = new CountryModel[]{new CountryModel("AD", "Andorra", "+376", R.drawable.flag_ad, "EUR"), new CountryModel("AE", "United Arab Emirates", "+971", R.drawable.flag_ae, "AED"), new CountryModel("AF", "Afghanistan", "+93", R.drawable.flag_af, "AFN"), new CountryModel("AG", "Antigua and Barbuda", "+1", R.drawable.flag_ag, "XCD"), new CountryModel("AI", "Anguilla", "+1", R.drawable.flag_ai, "XCD"), new CountryModel("AL", "Albania", "+355", R.drawable.flag_al, "ALL"), new CountryModel("AM", "Armenia", "+374", R.drawable.flag_am, "AMD"), new CountryModel("AO", "Angola", "+244", R.drawable.flag_ao, "AOA"), new CountryModel("AQ", "Antarctica", "+672", R.drawable.flag_aq, "USD"), new CountryModel("AR", "Argentina", "+54", R.drawable.flag_ar, "ARS"), new CountryModel("AS", "American Samoa", "+1", R.drawable.flag_as, "USD"), new CountryModel("AT", "Austria", "+43", R.drawable.flag_at, "EUR"), new CountryModel("AU", "Australia", "+61", R.drawable.flag_au, "AUD"), new CountryModel("AW", "Aruba", "+297", R.drawable.flag_aw, "AWG"), new CountryModel("AX", "Aland Islands", "+358", R.drawable.flag_ax, "EUR"), new CountryModel("AZ", "Azerbaijan", "+994", R.drawable.flag_az, "AZN"), new CountryModel("BA", "Bosnia and Herzegovina", "+387", R.drawable.flag_ba, "BAM"), new CountryModel("BB", "Barbados", "+1", R.drawable.flag_bb, "BBD"), new CountryModel("BD", "Bangladesh", "+880", R.drawable.flag_bd, "BDT"), new CountryModel("BE", "Belgium", "+32", R.drawable.flag_be, "EUR"), new CountryModel("BF", "Burkina Faso", "+226", R.drawable.flag_bf, "XOF"), new CountryModel("BG", "Bulgaria", "+359", R.drawable.flag_bg, "BGN"), new CountryModel("BH", "Bahrain", "+973", R.drawable.flag_bh, "BHD"), new CountryModel("BI", "Burundi", "+257", R.drawable.flag_bi, "BIF"), new CountryModel("BJ", "Benin", "+229", R.drawable.flag_bj, "XOF"), new CountryModel("BL", "Saint Barthelemy", "+590", R.drawable.flag_bl, "EUR"), new CountryModel("BM", "Bermuda", "+1", R.drawable.flag_bm, "BMD"), new CountryModel("BN", "Brunei Darussalam", "+673", R.drawable.flag_bn, "BND"), new CountryModel("BO", "Bolivia, Plurinational State of", "+591", R.drawable.flag_bo, "BOB"), new CountryModel("BQ", "Bonaire", "+599", R.drawable.flag_bq, "USD"), new CountryModel("BR", "Brazil", "+55", R.drawable.flag_br, "BRL"), new CountryModel("BS", "Bahamas", "+1", R.drawable.flag_bs, "BSD"), new CountryModel("BT", "Bhutan", "+975", R.drawable.flag_bt, "BTN"), new CountryModel("BV", "Bouvet Island", "+47", R.drawable.flag_bv, "NOK"), new CountryModel("BW", "Botswana", "+267", R.drawable.flag_bw, "BWP"), new CountryModel("BY", "Belarus", "+375", R.drawable.flag_by, "BYR"), new CountryModel("BZ", "Belize", "+501", R.drawable.flag_bz, "BZD"), new CountryModel("CA", "Canada", "+1", R.drawable.flag_ca, "CAD"), new CountryModel("CC", "Cocos (Keeling) Islands", "+61", R.drawable.flag_cc, "AUD"), new CountryModel("CD", "Congo, The Democratic Republic of the", "+243", R.drawable.flag_cd, "CDF"), new CountryModel("CF", "Central African Republic", "+236", R.drawable.flag_cf, "XAF"), new CountryModel("CG", "Congo", "+242", R.drawable.flag_cg, "XAF"), new CountryModel("CH", "Switzerland", "+41", R.drawable.flag_ch, "CHF"), new CountryModel("CI", "Ivory Coast", "+225", R.drawable.flag_ci, "XOF"), new CountryModel("CK", "Cook Islands", "+682", R.drawable.flag_ck, "NZD"), new CountryModel("CL", "Chile", "+56", R.drawable.flag_cl, "CLP"), new CountryModel("CM", "Cameroon", "+237", R.drawable.flag_cm, "XAF"), new CountryModel("CN", "China", "+86", R.drawable.flag_cn, "CNY"), new CountryModel("CO", "Colombia", "+57", R.drawable.flag_co, "COP"), new CountryModel("CR", "Costa Rica", "+506", R.drawable.flag_cr, "CRC"), new CountryModel("CU", "Cuba", "+53", R.drawable.flag_cu, "CUP"), new CountryModel("CV", "Cape Verde", "+238", R.drawable.flag_cv, "CVE"), new CountryModel("CW", "Curacao", "+599", R.drawable.flag_cw, "ANG"), new CountryModel("CX", "Christmas Island", "+61", R.drawable.flag_cx, "AUD"), new CountryModel("CY", "Cyprus", "+357", R.drawable.flag_cy, "EUR"), new CountryModel("CZ", "Czech Republic", "+420", R.drawable.flag_cz, "CZK"), new CountryModel("DE", "Germany", "+49", R.drawable.flag_de, "EUR"), new CountryModel("DJ", "Djibouti", "+253", R.drawable.flag_dj, "DJF"), new CountryModel("DK", "Denmark", "+45", R.drawable.flag_dk, "DKK"), new CountryModel("DM", "Dominica", "+1", R.drawable.flag_dm, "XCD"), new CountryModel("DO", "Dominican Republic", "+1", R.drawable.flag_do, "DOP"), new CountryModel("DZ", "Algeria", "+213", R.drawable.flag_dz, "DZD"), new CountryModel("EC", "Ecuador", "+593", R.drawable.flag_ec, "USD"), new CountryModel("EE", "Estonia", "+372", R.drawable.flag_ee, "EUR"), new CountryModel("EG", "Egypt", "+20", R.drawable.flag_eg, "EGP"), new CountryModel("EH", "Western Sahara", "+212", R.drawable.flag_eh, "MAD"), new CountryModel("ER", "Eritrea", "+291", R.drawable.flag_er, "ERN"), new CountryModel("ES", "Spain", "+34", R.drawable.flag_es, "EUR"), new CountryModel("ET", "Ethiopia", "+251", R.drawable.flag_et, "ETB"), new CountryModel("FI", "Finland", "+358", R.drawable.flag_fi, "EUR"), new CountryModel("FJ", "Fiji", "+679", R.drawable.flag_fj, "FJD"), new CountryModel("FK", "Falkland Islands (Malvinas)", "+500", R.drawable.flag_fk, "FKP"), new CountryModel("FM", "Micronesia, Federated States of", "+691", R.drawable.flag_fm, "USD"), new CountryModel("FO", "Faroe Islands", "+298", R.drawable.flag_fo, "DKK"), new CountryModel("FR", "France", "+33", R.drawable.flag_fr, "EUR"), new CountryModel("GA", "Gabon", "+241", R.drawable.flag_ga, "XAF"), new CountryModel("GB", "United Kingdom", "+44", R.drawable.flag_gb, "GBP"), new CountryModel("GD", "Grenada", "+1", R.drawable.flag_gd, "XCD"), new CountryModel("GE", "Georgia", "+995", R.drawable.flag_ge, "GEL"), new CountryModel("GF", "French Guiana", "+594", R.drawable.flag_gf, "EUR"), new CountryModel("GG", "Guernsey", "+44", R.drawable.flag_gg, "GGP"), new CountryModel("GH", "Ghana", "+233", R.drawable.flag_gh, "GHS"), new CountryModel("GI", "Gibraltar", "+350", R.drawable.flag_gi, "GIP"), new CountryModel("GL", "Greenland", "+299", R.drawable.flag_gl, "DKK"), new CountryModel("GM", "Gambia", "+220", R.drawable.flag_gm, "GMD"), new CountryModel("GN", "Guinea", "+224", R.drawable.flag_gn, "GNF"), new CountryModel("GP", "Guadeloupe", "+590", R.drawable.flag_gp, "EUR"), new CountryModel("GQ", "Equatorial Guinea", "+240", R.drawable.flag_gq, "XAF"), new CountryModel("GR", "Greece", "+30", R.drawable.flag_gr, "EUR"), new CountryModel("GS", "South Georgia and the South Sandwich Islands", "+500", R.drawable.flag_gs, "GBP"), new CountryModel("GT", "Guatemala", "+502", R.drawable.flag_gt, "GTQ"), new CountryModel("GU", "Guam", "+1", R.drawable.flag_gu, "USD"), new CountryModel("GW", "Guinea-Bissau", "+245", R.drawable.flag_gw, "XOF"), new CountryModel("GY", "Guyana", "+595", R.drawable.flag_gy, "GYD"), new CountryModel("HK", "Hong Kong", "+852", R.drawable.flag_hk, "HKD"), new CountryModel("HM", "Heard Island and McDonald Islands", "+000", R.drawable.flag_hm, "AUD"), new CountryModel("HN", "Honduras", "+504", R.drawable.flag_hn, "HNL"), new CountryModel("HR", "Croatia", "+385", R.drawable.flag_hr, "HRK"), new CountryModel("HT", "Haiti", "+509", R.drawable.flag_ht, "HTG"), new CountryModel("HU", "Hungary", "+36", R.drawable.flag_hu, "HUF"), new CountryModel("ID", "Indonesia", "+62", R.drawable.flag_id, "IDR"), new CountryModel("IE", "Ireland", "+353", R.drawable.flag_ie, "EUR"), new CountryModel("IL", "Israel", "+972", R.drawable.flag_il, "ILS"), new CountryModel("IM", "Isle of Man", "+44", R.drawable.flag_im, "GBP"), new CountryModel("IN", "India", "+91", R.drawable.flag_in, "INR"), new CountryModel("IO", "British Indian Ocean Territory", "+246", R.drawable.flag_io, "USD"), new CountryModel("IQ", "Iraq", "+964", R.drawable.flag_iq, "IQD"), new CountryModel("IR", "Iran, Islamic Republic of", "+98", R.drawable.flag_ir, "IRR"), new CountryModel("IS", "Iceland", "+354", R.drawable.flag_is, "ISK"), new CountryModel("IT", "Italy", "+39", R.drawable.flag_it, "EUR"), new CountryModel("JE", "Jersey", "+44", R.drawable.flag_je, "JEP"), new CountryModel("JM", "Jamaica", "+1", R.drawable.flag_jm, "JMD"), new CountryModel("JO", "Jordan", "+962", R.drawable.flag_jo, "JOD"), new CountryModel("JP", "Japan", "+81", R.drawable.flag_jp, "JPY"), new CountryModel("KE", "Kenya", "+254", R.drawable.flag_ke, "KES"), new CountryModel("KG", "Kyrgyzstan", "+996", R.drawable.flag_kg, "KGS"), new CountryModel("KH", "Cambodia", "+855", R.drawable.flag_kh, "KHR"), new CountryModel("KI", "Kiribati", "+686", R.drawable.flag_ki, "AUD"), new CountryModel("KM", "Comoros", "+269", R.drawable.flag_km, "KMF"), new CountryModel("KN", "Saint Kitts and Nevis", "+1", R.drawable.flag_kn, "XCD"), new CountryModel("KP", "North Korea", "+850", R.drawable.flag_kp, "KPW"), new CountryModel("KR", "South Korea", "+82", R.drawable.flag_kr, "KRW"), new CountryModel("KW", "Kuwait", "+965", R.drawable.flag_kw, "KWD"), new CountryModel("KY", "Cayman Islands", "+345", R.drawable.flag_ky, "KYD"), new CountryModel("KZ", "Kazakhstan", "+7", R.drawable.flag_kz, "KZT"), new CountryModel("LA", "Lao People's Democratic Republic", "+856", R.drawable.flag_la, "LAK"), new CountryModel("LB", "Lebanon", "+961", R.drawable.flag_lb, "LBP"), new CountryModel("LC", "Saint Lucia", "+1", R.drawable.flag_lc, "XCD"), new CountryModel("LI", "Liechtenstein", "+423", R.drawable.flag_li, "CHF"), new CountryModel("LK", "Sri Lanka", "+94", R.drawable.flag_lk, "LKR"), new CountryModel("LR", "Liberia", "+231", R.drawable.flag_lr, "LRD"), new CountryModel("LS", "Lesotho", "+266", R.drawable.flag_ls, "LSL"), new CountryModel("LT", "Lithuania", "+370", R.drawable.flag_lt, "LTL"), new CountryModel("LU", "Luxembourg", "+352", R.drawable.flag_lu, "EUR"), new CountryModel("LV", "Latvia", "+371", R.drawable.flag_lv, "LVL"), new CountryModel("LY", "Libyan Arab Jamahiriya", "+218", R.drawable.flag_ly, "LYD"), new CountryModel("MA", "Morocco", "+212", R.drawable.flag_ma, "MAD"), new CountryModel("MC", "Monaco", "+377", R.drawable.flag_mc, "EUR"), new CountryModel("MD", "Moldova, Republic of", "+373", R.drawable.flag_md, "MDL"), new CountryModel("ME", "Montenegro", "+382", R.drawable.flag_me, "EUR"), new CountryModel("MF", "Saint Martin", "+590", R.drawable.flag_mf, "EUR"), new CountryModel("MG", "Madagascar", "+261", R.drawable.flag_mg, "MGA"), new CountryModel("MH", "Marshall Islands", "+692", R.drawable.flag_mh, "USD"), new CountryModel("MK", "Macedonia, The Former Yugoslav Republic of", "+389", R.drawable.flag_mk, "MKD"), new CountryModel("ML", "Mali", "+223", R.drawable.flag_ml, "XOF"), new CountryModel("MM", "Myanmar", "+95", R.drawable.flag_mm, "MMK"), new CountryModel("MN", "Mongolia", "+976", R.drawable.flag_mn, "MNT"), new CountryModel("MO", "Macao", "+853", R.drawable.flag_mo, "MOP"), new CountryModel("MP", "Northern Mariana Islands", "+1", R.drawable.flag_mp, "USD"), new CountryModel("MQ", "Martinique", "+596", R.drawable.flag_mq, "EUR"), new CountryModel("MR", "Mauritania", "+222", R.drawable.flag_mr, "MRO"), new CountryModel("MS", "Montserrat", "+1", R.drawable.flag_ms, "XCD"), new CountryModel("MT", "Malta", "+356", R.drawable.flag_mt, "EUR"), new CountryModel("MU", "Mauritius", "+230", R.drawable.flag_mu, "MUR"), new CountryModel("MV", "Maldives", "+960", R.drawable.flag_mv, "MVR"), new CountryModel("MW", "Malawi", "+265", R.drawable.flag_mw, "MWK"), new CountryModel("MX", "Mexico", "+52", R.drawable.flag_mx, "MXN"), new CountryModel("MY", "Malaysia", "+60", R.drawable.flag_my, "MYR"), new CountryModel("MZ", "Mozambique", "+258", R.drawable.flag_mz, "MZN"), new CountryModel("NA", "Namibia", "+264", R.drawable.flag_na, "NAD"), new CountryModel("NC", "New Caledonia", "+687", R.drawable.flag_nc, "XPF"), new CountryModel("NE", "Niger", "+227", R.drawable.flag_ne, "XOF"), new CountryModel("NF", "Norfolk Island", "+672", R.drawable.flag_nf, "AUD"), new CountryModel("NG", "Nigeria", "+234", R.drawable.flag_ng, "NGN"), new CountryModel("NI", "Nicaragua", "+505", R.drawable.flag_ni, "NIO"), new CountryModel("NL", "Netherlands", "+31", R.drawable.flag_nl, "EUR"), new CountryModel("NO", "Norway", "+47", R.drawable.flag_no, "NOK"), new CountryModel("NP", "Nepal", "+977", R.drawable.flag_np, "NPR"), new CountryModel("NR", "Nauru", "+674", R.drawable.flag_nr, "AUD"), new CountryModel("NU", "Niue", "+683", R.drawable.flag_nu, "NZD"), new CountryModel("NZ", "New Zealand", "+64", R.drawable.flag_nz, "NZD"), new CountryModel("OM", "Oman", "+968", R.drawable.flag_om, "OMR"), new CountryModel("PA", "Panama", "+507", R.drawable.flag_pa, "PAB"), new CountryModel("PE", "Peru", "+51", R.drawable.flag_pe, "PEN"), new CountryModel("PF", "French Polynesia", "+689", R.drawable.flag_pf, "XPF"), new CountryModel("PG", "Papua New Guinea", "+675", R.drawable.flag_pg, "PGK"), new CountryModel("PH", "Philippines", "+63", R.drawable.flag_ph, "PHP"), new CountryModel("PK", "Pakistan", "+92", R.drawable.flag_pk, "PKR"), new CountryModel("PL", "Poland", "+48", R.drawable.flag_pl, "PLN"), new CountryModel("PM", "Saint Pierre and Miquelon", "+508", R.drawable.flag_pm, "EUR"), new CountryModel("PN", "Pitcairn", "+872", R.drawable.flag_pn, "NZD"), new CountryModel("PR", "Puerto Rico", "+1", R.drawable.flag_pr, "USD"), new CountryModel("PS", "Palestinian Territory, Occupied", "+970", R.drawable.flag_ps, "ILS"), new CountryModel("PT", "Portugal", "+351", R.drawable.flag_pt, "EUR"), new CountryModel("PW", "Palau", "+680", R.drawable.flag_pw, "USD"), new CountryModel("PY", "Paraguay", "+595", R.drawable.flag_py, "PYG"), new CountryModel("QA", "Qatar", "+974", R.drawable.flag_qa, "QAR"), new CountryModel("RE", "Reunion", "+262", R.drawable.flag_re, "EUR"), new CountryModel("RO", "Romania", "+40", R.drawable.flag_ro, "RON"), new CountryModel("RS", "Serbia", "+381", R.drawable.flag_rs, "RSD"), new CountryModel("RU", "Russia", "+7", R.drawable.flag_ru, "RUB"), new CountryModel("RW", "Rwanda", "+250", R.drawable.flag_rw, "RWF"), new CountryModel("SA", "Saudi Arabia", "+966", R.drawable.flag_sa, "SAR"), new CountryModel("SB", "Solomon Islands", "+677", R.drawable.flag_sb, "SBD"), new CountryModel("SC", "Seychelles", "+248", R.drawable.flag_sc, "SCR"), new CountryModel("SD", "Sudan", "+249", R.drawable.flag_sd, "SDG"), new CountryModel("SE", "Sweden", "+46", R.drawable.flag_se, "SEK"), new CountryModel("SG", "Singapore", "+65", R.drawable.flag_sg, "SGD"), new CountryModel("SH", "Saint Helena, Ascension and Tristan Da Cunha", "+290", R.drawable.flag_sh, "SHP"), new CountryModel("SI", "Slovenia", "+386", R.drawable.flag_si, "EUR"), new CountryModel("SJ", "Svalbard and Jan Mayen", "+47", R.drawable.flag_sj, "NOK"), new CountryModel("SK", "Slovakia", "+421", R.drawable.flag_sk, "EUR"), new CountryModel("SL", "Sierra Leone", "+232", R.drawable.flag_sl, "SLL"), new CountryModel("SM", "San Marino", "+378", R.drawable.flag_sm, "EUR"), new CountryModel("SN", "Senegal", "+221", R.drawable.flag_sn, "XOF"), new CountryModel("SO", "Somalia", "+252", R.drawable.flag_so, "SOS"), new CountryModel("SR", "Suriname", "+597", R.drawable.flag_sr, "SRD"), new CountryModel("SS", "South Sudan", "+211", R.drawable.flag_ss, "SSP"), new CountryModel("ST", "Sao Tome and Principe", "+239", R.drawable.flag_st, "STD"), new CountryModel("SV", "El Salvador", "+503", R.drawable.flag_sv, "SVC"), new CountryModel("SX", "Sint Maarten", "+1", R.drawable.flag_sx, "ANG"), new CountryModel("SY", "Syrian Arab Republic", "+963", R.drawable.flag_sy, "SYP"), new CountryModel("SZ", "Swaziland", "+268", R.drawable.flag_sz, "SZL"), new CountryModel("TC", "Turks and Caicos Islands", "+1", R.drawable.flag_tc, "USD"), new CountryModel("TD", "Chad", "+235", R.drawable.flag_td, "XAF"), new CountryModel("TF", "French Southern Territories", "+262", R.drawable.flag_tf, "EUR"), new CountryModel("TG", "Togo", "+228", R.drawable.flag_tg, "XOF"), new CountryModel("TH", "Thailand", "+66", R.drawable.flag_th, "THB"), new CountryModel("TJ", "Tajikistan", "+992", R.drawable.flag_tj, "TJS"), new CountryModel("TK", "Tokelau", "+690", R.drawable.flag_tk, "NZD"), new CountryModel("TL", "East Timor", "+670", R.drawable.flag_tl, "USD"), new CountryModel("TM", "Turkmenistan", "+993", R.drawable.flag_tm, "TMT"), new CountryModel("TN", "Tunisia", "+216", R.drawable.flag_tn, "TND"), new CountryModel("TO", "Tonga", "+676", R.drawable.flag_to, "TOP"), new CountryModel("TR", "Turkey", "+90", R.drawable.flag_tr, "TRY"), new CountryModel("TT", "Trinidad and Tobago", "+1", R.drawable.flag_tt, "TTD"), new CountryModel("TV", "Tuvalu", "+688", R.drawable.flag_tv, "AUD"), new CountryModel("TW", "Taiwan", "+886", R.drawable.flag_tw, "TWD"), new CountryModel("TZ", "Tanzania, United Republic of", "+255", R.drawable.flag_tz, "TZS"), new CountryModel("UA", "Ukraine", "+380", R.drawable.flag_ua, "UAH"), new CountryModel("UG", "Uganda", "+256", R.drawable.flag_ug, "UGX"), new CountryModel("UM", "U.S. Minor Outlying Islands", "+1", R.drawable.flag_um, "USD"), new CountryModel("US", "United States", "+1", R.drawable.flag_us, "USD"), new CountryModel("UY", "Uruguay", "+598", R.drawable.flag_uy, "UYU"), new CountryModel("UZ", "Uzbekistan", "+998", R.drawable.flag_uz, "UZS"), new CountryModel("VA", "Holy See (Vatican City State)", "+379", R.drawable.flag_va, "EUR"), new CountryModel("VC", "Saint Vincent and the Grenadines", "+1", R.drawable.flag_vc, "XCD"), new CountryModel("VE", "Venezuela, Bolivarian Republic of", "+58", R.drawable.flag_ve, "VEF"), new CountryModel("VG", "Virgin Islands, British", "+1", R.drawable.flag_vg, "USD"), new CountryModel("VI", "Virgin Islands, U.S.", "+1", R.drawable.flag_vi, "USD"), new CountryModel("VN", "Vietnam", "+84", R.drawable.flag_vn, "VND"), new CountryModel("VU", "Vanuatu", "+678", R.drawable.flag_vu, "VUV"), new CountryModel("WF", "Wallis and Futuna", "+681", R.drawable.flag_wf, "XPF"), new CountryModel("WS", "Samoa", "+685", R.drawable.flag_ws, "WST"), new CountryModel("XK", "Kosovo", "+383", R.drawable.flag_xk, "EUR"), new CountryModel("YE", "Yemen", "+967", R.drawable.flag_ye, "YER"), new CountryModel("YT", "Mayotte", "+262", R.drawable.flag_yt, "EUR"), new CountryModel("ZA", "South Africa", "+27", R.drawable.flag_za, "ZAR"), new CountryModel("ZM", "Zambia", "+260", R.drawable.flag_zm, "ZMW"), new CountryModel("ZW", "Zimbabwe", "+263", R.drawable.flag_zw, "USD")};
    private List<CountryModel> countryModelList = new ArrayList<>();
    private CountriesAdapter countriesAdapter;
    private AlertDialog dialog;
    private String lang;
    private String phone_code = "+966";

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }


    private void initView() {
        model = new LoginModel();
        countryModelList = new ArrayList<>(Arrays.asList(countries));

        binding.setModel(model);
        presenter = new ActivityLoginPresenter(this, this);
        binding.btlogin.setOnClickListener(view -> {
            presenter.checkData(model);
        });

        sortCountries();
        createCountriesDialog();
        binding.imagecountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCountryDialog();
            }
        });
    }


    private void createCountriesDialog() {

        dialog = new AlertDialog.Builder(this)
                .create();
        countriesAdapter = new CountriesAdapter(countryModelList, this);

        DialogCountriesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_countries, null, false);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(countriesAdapter);

        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_congratulation_animation;
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_window_bg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(binding.getRoot());
    }

    private void sortCountries() {
        Collections.sort(countryModelList, (country1, country2) -> {
            return country1.getName().trim().compareToIgnoreCase(country2.getName().trim());
        });
    }

    @Override
    public void onLoginValid() {
        Intent intent = new Intent(this, ConfirmCodeActivity.class);
        intent.putExtra("phone_code", model.getPhone_code());
        intent.putExtra("phone", model.getPhone());

        startActivity(intent);
        finish();

    }

    public void showCountryDialog() {
        dialog.show();
    }


    public void setItemData(CountryModel countryModel) {
        dialog.dismiss();
        phone_code = countryModel.getDialCode();
        binding.imagecountry.setImageResource(countryModel.getFlag());
        model.setPhone_code(countryModel.getDialCode());
        binding.setModel(model);
        // binding.imageFlag.setImageResource(countryModel.getFlag());
    }

}