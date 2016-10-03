package com.tabeeby.doctor.GsonModels;

import java.util.List;

/**
 * Created by Z510 on 9/29/2016.
 */

public class CountryModel {

    private DataBean data;
    /**
     * data : {"country_list":[{"id":"344","title":"Afghanistan"},{"id":"345","title":"Albania"},{"id":"346","title":"Algeria"},{"id":"347","title":"American Samoa"},{"id":"348","title":"Andorra"},{"id":"349","title":"Angola"},{"id":"350","title":"Anguilla"},{"id":"351","title":"Antarctica"},{"id":"352","title":"Antigua and Barbuda"},{"id":"353","title":"Argentina"},{"id":"354","title":"Armenia"},{"id":"355","title":"Aruba"},{"id":"356","title":"Australia"},{"id":"357","title":"Austria"},{"id":"358","title":"Azerbaijan"},{"id":"359","title":"Bahamas"},{"id":"360","title":"Bahrain"},{"id":"361","title":"Bangladesh"},{"id":"362","title":"Barbados"},{"id":"363","title":"Belarus"},{"id":"364","title":"Belgium"},{"id":"365","title":"Belize"},{"id":"366","title":"Benin"},{"id":"367","title":"Bermuda"},{"id":"368","title":"Bhutan"},{"id":"369","title":"Bolivia"},{"id":"370","title":"Bosnia and Herzegowina"},{"id":"371","title":"Botswana"},{"id":"372","title":"Bouvet Island"},{"id":"373","title":"Brazil"},{"id":"374","title":"British Indian Ocean Territory"},{"id":"375","title":"Brunei Darussalam"},{"id":"376","title":"Bulgaria"},{"id":"377","title":"Burkina Faso"},{"id":"378","title":"Burundi"},{"id":"379","title":"Cambodia"},{"id":"380","title":"Cameroon"},{"id":"381","title":"Canada"},{"id":"382","title":"Cape Verde"},{"id":"383","title":"Cayman Islands"},{"id":"384","title":"Central African Republic"},{"id":"385","title":"Chad"},{"id":"386","title":"Chile"},{"id":"387","title":"China"},{"id":"388","title":"Christmas Island"},{"id":"389","title":"Cocos (Keeling) Islands"},{"id":"390","title":"Colombia"},{"id":"391","title":"Comoros"},{"id":"392","title":"Congo"},{"id":"393","title":"Congo, the Democratic Republic of the"},{"id":"394","title":"Cook Islands"},{"id":"395","title":"Costa Rica"},{"id":"396","title":"Cote d'Ivoire"},{"id":"397","title":"Croatia (Hrvatska)"},{"id":"398","title":"Cuba"},{"id":"399","title":"Cyprus"},{"id":"400","title":"Czech Republic"},{"id":"401","title":"Denmark"},{"id":"402","title":"Djibouti"},{"id":"403","title":"Dominica"},{"id":"404","title":"Dominican Republic"},{"id":"405","title":"East Timor"},{"id":"406","title":"Ecuador"},{"id":"407","title":"Egypt"},{"id":"408","title":"El Salvador"},{"id":"409","title":"Equatorial Guinea"},{"id":"410","title":"Eritrea"},{"id":"411","title":"Estonia"},{"id":"412","title":"Ethiopia"},{"id":"413","title":"Falkland Islands (Malvinas)"},{"id":"414","title":"Faroe Islands"},{"id":"415","title":"Fiji"},{"id":"416","title":"Finland"},{"id":"417","title":"France"},{"id":"418","title":"France Metropolitan"},{"id":"419","title":"French Guiana"},{"id":"420","title":"French Polynesia"},{"id":"421","title":"French Southern Territories"},{"id":"422","title":"Gabon"},{"id":"423","title":"Gambia"},{"id":"424","title":"Georgia"},{"id":"425","title":"Germany"},{"id":"426","title":"Ghana"},{"id":"427","title":"Gibraltar"},{"id":"428","title":"Greece"},{"id":"429","title":"Greenland"},{"id":"430","title":"Grenada"},{"id":"431","title":"Guadeloupe"},{"id":"432","title":"Guam"},{"id":"433","title":"Guatemala"},{"id":"434","title":"Guinea"},{"id":"435","title":"Guinea-Bissau"},{"id":"436","title":"Guyana"},{"id":"437","title":"Haiti"},{"id":"438","title":"Heard and Mc Donald Islands"},{"id":"439","title":"Holy See (Vatican City State)"},{"id":"440","title":"Honduras"},{"id":"441","title":"Hong Kong"},{"id":"442","title":"Hungary"},{"id":"443","title":"Iceland"},{"id":"444","title":"India"},{"id":"445","title":"Indonesia"},{"id":"446","title":"Iran (Islamic Republic of)"},{"id":"447","title":"Iraq"},{"id":"448","title":"Ireland"},{"id":"449","title":"Israel"},{"id":"450","title":"Italy"},{"id":"451","title":"Jamaica"},{"id":"452","title":"Japan"},{"id":"453","title":"Jordan"},{"id":"454","title":"Kazakhstan"},{"id":"455","title":"Kenya"},{"id":"456","title":"Kiribati"},{"id":"457","title":"Korea, Democratic People's Republic of"},{"id":"458","title":"Korea, Republic of"},{"id":"459","title":"Kuwait"},{"id":"460","title":"Kyrgyzstan"},{"id":"461","title":"Lao, People's Democratic Republic"},{"id":"462","title":"Latvia"},{"id":"463","title":"Lebanon"},{"id":"464","title":"Lesotho"},{"id":"465","title":"Liberia"},{"id":"466","title":"Libyan Arab Jamahiriya"},{"id":"467","title":"Liechtenstein"},{"id":"468","title":"Lithuania"},{"id":"469","title":"Luxembourg"},{"id":"470","title":"Macau"},{"id":"471","title":"Macedonia, The Former Yugoslav Republic of"},{"id":"472","title":"Madagascar"},{"id":"473","title":"Malawi"},{"id":"474","title":"Malaysia"},{"id":"475","title":"Maldives"},{"id":"476","title":"Mali"},{"id":"477","title":"Malta"},{"id":"478","title":"Marshall Islands"},{"id":"479","title":"Martinique"},{"id":"480","title":"Mauritania"},{"id":"481","title":"Mauritius"},{"id":"482","title":"Mayotte"},{"id":"483","title":"Mexico"},{"id":"484","title":"Micronesia, Federated States of"},{"id":"485","title":"Moldova, Republic of"},{"id":"486","title":"Monaco"},{"id":"487","title":"Mongolia"},{"id":"488","title":"Montserrat"},{"id":"489","title":"Morocco"},{"id":"490","title":"Mozambique"},{"id":"491","title":"Myanmar"},{"id":"492","title":"Namibia"},{"id":"493","title":"Nauru"},{"id":"494","title":"Nepal"},{"id":"495","title":"Netherlands"},{"id":"496","title":"Netherlands Antilles"},{"id":"497","title":"New Caledonia"},{"id":"498","title":"New Zealand"},{"id":"499","title":"Nicaragua"},{"id":"500","title":"Niger"},{"id":"501","title":"Nigeria"},{"id":"502","title":"Niue"},{"id":"503","title":"Norfolk Island"},{"id":"504","title":"Northern Mariana Islands"},{"id":"505","title":"Norway"},{"id":"506","title":"Oman"},{"id":"507","title":"Pakistan"},{"id":"508","title":"Palau"},{"id":"509","title":"Panama"},{"id":"510","title":"Papua New Guinea"},{"id":"511","title":"Paraguay"},{"id":"512","title":"Peru"},{"id":"513","title":"Philippines"},{"id":"514","title":"Pitcairn"},{"id":"515","title":"Poland"},{"id":"516","title":"Portugal"},{"id":"517","title":"Puerto Rico"},{"id":"518","title":"Qatar"},{"id":"519","title":"Reunion"},{"id":"520","title":"Romania"},{"id":"521","title":"Russian Federation"},{"id":"522","title":"Rwanda"},{"id":"523","title":"Saint Kitts and Nevis"},{"id":"524","title":"Saint Lucia"},{"id":"525","title":"Saint Vincent and the Grenadines"},{"id":"526","title":"Samoa"},{"id":"527","title":"San Marino"},{"id":"528","title":"Sao Tome and Principe"},{"id":"529","title":"Saudi Arabia"},{"id":"530","title":"Senegal"},{"id":"531","title":"Seychelles"},{"id":"532","title":"Sierra Leone"},{"id":"533","title":"Singapore"},{"id":"534","title":"Slovakia (Slovak Republic)"},{"id":"535","title":"Slovenia"},{"id":"536","title":"Solomon Islands"},{"id":"537","title":"Somalia"},{"id":"538","title":"South Africa"},{"id":"539","title":"South Georgia and the South Sandwich Islands"},{"id":"540","title":"Spain"},{"id":"541","title":"Sri Lanka"},{"id":"542","title":"St. Helena"},{"id":"543","title":"St. Pierre and Miquelon"},{"id":"544","title":"Sudan"},{"id":"545","title":"Suriname"},{"id":"546","title":"Svalbard and Jan Mayen Islands"},{"id":"547","title":"Swaziland"},{"id":"548","title":"Sweden"},{"id":"549","title":"Switzerland"},{"id":"550","title":"Syrian Arab Republic"},{"id":"551","title":"Taiwan, Province of China"},{"id":"552","title":"Tajikistan"},{"id":"553","title":"Tanzania, United Republic of"},{"id":"554","title":"Thailand"},{"id":"555","title":"Togo"},{"id":"556","title":"Tokelau"},{"id":"557","title":"Tonga"},{"id":"558","title":"Trinidad and Tobago"},{"id":"559","title":"Tunisia"},{"id":"560","title":"Turkey"},{"id":"561","title":"Turkmenistan"},{"id":"562","title":"Turks and Caicos Islands"},{"id":"563","title":"Tuvalu"},{"id":"564","title":"Uganda"},{"id":"565","title":"Ukraine"},{"id":"566","title":"United Arab Emirates"},{"id":"567","title":"United Kingdom"},{"id":"568","title":"United States"},{"id":"569","title":"United States Minor Outlying Islands"},{"id":"570","title":"Uruguay"},{"id":"571","title":"Uzbekistan"},{"id":"572","title":"Vanuatu"},{"id":"573","title":"Venezuela"},{"id":"574","title":"Vietnam"},{"id":"575","title":"Virgin Islands (British)"},{"id":"576","title":"Virgin Islands (U.S.)"},{"id":"577","title":"Wallis and Futuna Islands"},{"id":"578","title":"Western Sahara"},{"id":"579","title":"Yemen"},{"id":"580","title":"Yugoslavia"},{"id":"581","title":"Zambia"},{"id":"582","title":"Zimbabwe"}]}
     * status : true
     * message : Retrieved countries in English
     * code : 378
     */

    private boolean status;
    private String message;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * id : 344
         * title : Afghanistan
         */

        private List<CountryListBean> country_list;

        public List<CountryListBean> getCountry_list() {
            return country_list;
        }

        public void setCountry_list(List<CountryListBean> country_list) {
            this.country_list = country_list;
        }

        public static class CountryListBean {
            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
