﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>裱花大师 - 登录</title>
    <link rel="Shortcut Icon" href="<c:url value="/resource/pic/icon.ico" />" />
    <script type="text/javascript" src="<c:url value="/resource/bootstrap/js/jquery-1.8.3.min.js"/>"></script>


    <script type="text/javascript" src="<c:url value="/resource/js/ga.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resource/js/register.js"/>"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/register.css"/>">

    <script>
        window.isMainPinterestSite = true;
        var Pc = {"PAGE_LOAD_REQUEST_IDENTIFIER": "786136468367", "categoryKeyToDisplayName": {"shop": "Shop our picks", "corgis": "Corgis", "fashion": "Fashion", "gardening": "Gardening", "health_fitness": "Health and fitness", "videos": "Videos", "people": "People", "art_arch": "Art and architecture", "weddings": "Weddings", "celebrities": "Celebrities", "quotes": "Quotes", "featured": "Featured", "hair_beauty": "Hair and beauty", "apparel": "Apparel and accessories", "home": "Home decor", "art": "Art", "education": "Education", "technology": "Technology", "humor": "Humor", "everything": "Everything", "science_nature": "Science and nature", "food_drink": "Food and drink", "sports": "Sports", "celebrities_public_figures": "Celebrities and public figures", "other": "Other", "pets": "Pets", "mens_fashion": "Men's fashion", "gift_guides": "Gift guides", "wedding_events": "Wedding and events", "prints_posters": "Print and posters", "tattoos": "Tattoos", "illustrations_posters": "Illustrations and posters", "shop_space": "Shop", "photography": "Photography", "outdoors": "Outdoors", "holidays": "Holidays", "geek": "Geek", "women_apparel": "Women's apparel", "for_dad": "For dad", "mylife": "My life", "gifts": "Gifts", "men_apparel": "Men's apparel", "diy_crafts": "DIY and crafts", "holidays_events": "Holidays and events", "commerce": "Shop", "kids": "Kids and parenting", "animals": "Animals and pets", "home_decor": "Home decor", "culture": "Culture", "science": "Science and nature", "film_music_books": "Film, music and books", "travel_places": "Travel and places", "womens_fashion": "Women's fashion", "products": "Products", "architecture": "Architecture", "cars_motorcycles": "Cars and motorcycles", "fitness": "Fitness", "popular": "Popular", "design": "Design", "home_improvement": "Home improvement", "travel": "Travel", "history": "History"}, "PAGE_ROUTE_PATTERN": "^\/login\/$", "IS_TEST_MODE": false, "expns": {}, "locale": "en-US", "reportPageLoadTimings": false, "pinterestRID": "786136468367", "authenticationOrigin": "https:\/\/www.pinterest.com", "trustedHostnameEnding": "pinterest.com", "experiments": {"active": {}, "triggerable": {"ads_lift_holdout_test_2": "enabled", "ads_related_pins": "enabled", "search_frontier_blend_v2": "control", "ads_lift_holdout_jie": "enabled", "interests_read_home_non_board_feed_from_interest_feed_v2": "control_category2", "pinnability_hive_ss_pfy2": "pfy_hive_ss11", "web_flashlight_closeup": "rollout", "ads_lift_holdout_test_30": "control", "pin_my_dream_backyard": "enabled", "smartfeed_commerce_blending_adjust": "enabled_in_pfy", "pinnability_ff_selection_experiment": "pinnability_i18n_combined_ff", "interests_use_victoria_for_category_feed": "enabled_single_tier3", "copytune_We_found_some_new_boards_for_you_em_OHLCZ": "variant_6", "fathersdayus": "enabled", "copytune_Your_Pin_is_now_buyable_email_dynam_JVEQR": "variant_2", "ads_lift_holdout_test_30b": "enabled", "interests_use_victoria_for_category_feed_v2": "control", "unauth_pin_closeup_gray": "enabled", "search_local_reset": "rollout", "ads_orgtrack_homefeed3": "control", "web_seo_nux_fpe4": "enabled_no_like", "smartfeed_interest_round_robin_pca": "overfetch_3x", "web_seo_nux_fpe3": "enabled", "enable_asterix_promoted_pins": "enabled", "ads_commerce_ranking": "enabled", "i18n_search_guides_es_es": "enabled_engagement", "email_dto_popular_pins_v1": "enabled_g1", "fbo_pinlist_web_2": "enabled", "weekly_email_remove_fallback_recs": "enabled", "pinnability_ff_longterm_controlb": "enabled", "pinnability_ff_longterm_controla": "control", "push-pins-waiting": "control", "custom_gender_at_signup_click": "enabled", "email_dto_pin_twins_v1": "enabled_g2", "sterling_1_4": "enabled", "search_boost_buyable_pins": "control", "smartfeed_blending_with_unused_source": "control", "ads_negative_boost_experiment1": "hides_3", "user_activity_blender_aggressive_tuning": "control", "email_dto_trending_searches_v1": "enabled_g1", "ads_lift_holdout_test": "enabled", "email_dto_dormant_board_recommendations_v1": "enabled_g2", "ab_dashboard_hide_results": "validate_failed", "back_to_school_parents_network_story_v2": "rollout", "search_commerce_cluster": "enabled", "i18n_search_guides_pt_br": "control_engagement", "new_gb_landing": "enabled", "copytune_Discover_new_ideas_from_your_Pin_tw_SRE2B__pt_BR": "custom_8", "email_dto_homefeed_new_pins_v1": "enabled_g1", "email_browserbutton_de": "enabled", "pinnability_hive_ss_ff2": "hive_repro_prod", "smartfeed_pinnability_topics_longterm": "pinnability_ranker", "android_show_nearby_pins_on_closeup": "enabled", "promote-app-install-pins": "control", "ads_remove_asterix_expander": "enabled", "pinnability_hive_source_specific": "pfy_hive_source_specific", "web_search_autocomplete": "enabled", "use_trk_endpoint_on_ios": "enabled", "ludo_test2": "control", "copytune_All_the_latest_news_from_people_you_KKHFE__ja_JP": "custom_variant_4", "android_inviter_with_facebook_messenger": "enabled", "recipe_metadata_amplification": "enabled", "smartfeed_increase_topic_pins": "3x_original", "pinnability_i18n_combined_ff": "i18n_1", "ads_lift_holdout_walgreens_photo": "enabled", "partner_confirm_website_nag": "control", "game_day_network_story": "rollout", "i18n_search_guides_fr_fr": "enabled", "example_simple_email_experiment": "control", "search_quality_q4_2015": "enabled", "smartfeed_user_activity_fallback": "fallback_after_4hrs", "ads_upper_bound_holdout_v4": "1_no_upperbound", "test_experiment": "control", "search_i18n_stopword": "control", "search_autocomplete_latency": "control", "maia2_migration": "control", "android_fpe_pulsars": "control_old_fpe", "no_cpc_ad_for_non_clickers": "enabled", "ads_broadmatch8": "search_similarity_coverage", "eu_cookie_banner": "enabled", "email_dto_board_create_recommendations_v1": "enabled_g1", "jp_ios_5": "enabled", "nux_smart_recs_v4": "enabled_v2", "sxiang_no_review_prod": "new_group", "promote-gif-pins": "control", "sterling_13": "enabled", "local_weekly_email_v3": "enabled", "discover_friends": "enabled2", "i18n_search_guides_es_mx": "enabled_engagement", "i18n_search_guides_de_de": "control", "search_commerce_bigram": "control", "copytune_Hey_first_name_Don_t_miss_out_on_th_E3EIY__de_DE": "custom_0", "email_ranking_and_dynamic_cooldown_v4": "enabled_v10_cd7", "ads_lift_holdout_test_cpe": "control", "ads_lift_holdout_test_cpc": "control", "email_dto_editorial_with_pins_v1": "enabled_g1", "email_ranking_and_dynamic_cooldown_rollout": "enabled", "interests_recommendation_manager_for_interest_rec_email": "enabled", "group_board_email": "new_email", "pin_my_dream_back_yard": "enabled", "movie_metadata_amplification": "enabled", "us_travel_staycation_email": "enabled", "ads_related_pins_term_v3": "2_use_stagingva_tp_ppid", "ads_related_pins_term_v2": "1_control", "new_user_group_board_email_v3": "control", "web_modular_closeup": "without-board-rep", "us_fathersday2": "enabled", "android_multistep_signup": "continue_email_ship", "search_country_boost_v2": "enabled", "expand_pin_twins_email": "enabled", "ads_lift_holdout_oshkosh": "enabled", "fathersday2_us_core_casual": "enabled", "copytune_This_week_s_top_Pins_email_dynamic__BG2BS": "variant_1", "email_dto_network_story_digest_v5": "enabled_g1", "fr-mothers-day-email": "enabled", "editorial_delivery_logic_experiment": "enabled_total_events", "email_dto_pin_on_another_board_v1": "enabled_g1", "partner_performance_hero_v1": "control2", "smartfeed_new_user_blending2": "enabled_pfy", "facebook_signup": "enabled", "bid_guidance_hero": "enabled", "new_fb_fields_v2": "control", "pinnability_hive_ss_topic2": "i181", "fantasio_realpin_read": "control_truncated2", "cart_abandonment_notification": "control", "more_fb_likes_v2": "process_100_old", "email_dto_interest_recommendations_v1": "enabled_g2", "branch_deeplinking": "enabled"}}, "canonicalMainDomain": "www.pinterest.com", "reportInteractionTimings": false, "DEBUG": false, "FB_KEY": "274266067164", "siteConst": {"PIN_MARGIN": 14, "MAX_COLUMNS": 10, "MIN_COLUMNS": 3, "PRESENTER_BUTTON_MODULE_NAME": "DropdownButton", "PIN_WIDTH": 236}};
        !function(e){function n(t){if(c[t])return c[t].exports;var f=c[t]={exports:{},id:t,loaded:!1};return e[t].call(f.exports,f,f.exports,n),f.loaded=!0,f.exports}var t=window.webpackJsonp;window.webpackJsonp=function o(c,a){for(var i,d,u=0,r=[];u<c.length;u++)d=c[u],f[d]&&r.push.apply(r,f[d]),f[d]=0;for(i in a)e[i]=a[i];for(t&&t(c,a);r.length;)r.shift().call(null,n)};var c={},f={69:0};return n.e=function a(e,t){if(0===f[e])return t.call(null,n);if(void 0!==f[e])f[e].push(t);else{f[e]=[t];var c=document.getElementsByTagName("head")[0],o=document.createElement("script");o.type="text/javascript",o.charset="utf-8",o.async=!0,o.src=n.p+"chunk."+{1:"510e0c391771f9134c7f",2:"5e0db2be5156a102541f",3:"4c6f0f4dbb2855c124e1",4:"bdcbf9f99c6d17d4fee5",5:"c3792602c6bd6af96577",6:"fc376a302df41469b484",9:"6187ff92cfb6adf79ba6",10:"382af363c9a2fda35776",11:"2a627fa3b73cdb038f0f",12:"d86e3d26e9ab0ce1ef6f",13:"e8eae94a655a50965a07",14:"e1e10609735700a2b2cd",15:"fdb169b45aa1fa8013a5",16:"18c510bfbba15e408685",17:"ae10039b3a61ebe878c4",18:"be27eab6c317118e2977",19:"95d31d0e5c402066a862",20:"d6be47e9683e5c78525d",21:"ec8f84e0b1fa14922ed9",22:"bebe3faa1ce3b51a8f33",23:"b5797a4d112b12100ee7",24:"4d6fadf91ffeab68bacf",25:"8462dfca23579aeb1682",26:"50d8697ddb1e333d225e",27:"29ea4d29361b267ec2df",28:"fde5c1e807c35737f12f",29:"99098688d4729a3f9987",30:"191b86c5faf5b2a6c6cb",31:"9564c492d9a44f075681",32:"17e6f677da76d7b4cca0",33:"caf17d0c5ada6386644b",34:"45b08c64089eca3bc52e",35:"05b49ed196acfb6c9696",36:"847625c039bccbf081ee",37:"3815952e020c07605d1c",38:"4418100bf89945546aa7",39:"c2fe5168c2aa5ad697b2",40:"ab38babc6e235d835e84",41:"23381c469a8a43719d9f",42:"479eae2a633fb7bc2525",43:"1eec88c452e693b50643",44:"16d319d14c263da0f818",45:"947b0e1896ffb9203371",46:"51991bb1fcd6a7b106f7",47:"0f4b2090c367b1868184",48:"37bf410088b621a26e96",49:"0d47139faa1dc31be861",50:"c6dfdec5fcf466a6a17f",51:"9ae6ea418ccab859d21c",52:"8b932a9659131ecf47af",53:"110e14f44147458f8d7a",54:"494262b2ec404c36692c",55:"c27fa272d719c4805eab",56:"85748054b6fd68721f9e",57:"ef67f11ce3955522b303",58:"e05aee083ff18fc69412",59:"0e4790360ea528d9852e",60:"44cf7a3838160845c44c",61:"fc24877206db9c9d4696",62:"7bb8fb66bab8e759af93",63:"6f351708dfde3d94a22b",64:"bb0974bf18c30179ba28",65:"3f4b87419670a24e03d9",66:"eed3f06a84d005636149",67:"17f6b6a5bda50c292602",68:"8ba90edfa0264c354e17",70:"dc35b7e0688a16658d29",71:"619e759d2eb7d4bbc8d4",73:"3d9a2d022f1a4cd80e61",74:"11b3c2316009c0ddcee3",75:"88f519a523f74793e1b6",76:"721dfdce62a68bf97390",77:"8890292f9a707fe8b1f4",78:"fa4ed373984035f9b83a",79:"b6b62262d0a0d01473db",81:"9292987e5b223e566aa1",82:"c6af5cc838173aaab872",83:"811df7fb78752b1d602f",84:"7719ed9bc92693677e69",85:"497a178fd3565257000c",86:"406416c3883416b22109",88:"519162f02508629ce821",89:"e9513a32785618c83826"}[e]+".js",c.appendChild(o)}},n.m=e,n.c=c,n.p="https://s-passets-cache-ak0.pinimg.com/webapp/js/",n(0)}({0:function(e,n,t){e.exports=t(1094)},2:function(e,n,t){window.P=window.P||{};var c=t(3);window.P.clickCapturer=new c,window.P.lazy=t(4),window.P.script=t(6);var f=t(8);f.adjust(Pc.siteConst.PIN_WIDTH,Pc.siteConst.PIN_MARGIN,Pc.siteConst.MIN_COLUMNS),window.P.staticFileUrls=t(9)},3:function(e,n){function t(e,n,t){i&&e.addEventListener(n,t,!1)}function c(e,n,t){i&&e.removeEventListener(n,t,!1)}function f(e){a=e}function o(){a=null}var a=null,i=!!document.addEventListener,d=function(){t(document,"click",f)};d.prototype.replayEvents=function(){if(c(document,"click",o),a){var e=a.target;e.click()}},d.prototype.stopCapturing=function(){c(document,"click",f),t(document,"click",o)},e.exports=d},4:function(e,n,t){var c=t(5),f=" loaded",o=" fade",a={onImageLoad:function(e){var n=f;c.isOverlappingViewport(e)&&(n+=o),e.className+=n,e.removeAttribute("data-load-state")},onImageError:function(e){e.setAttribute("data-load-state","error")}};e.exports=a},5:function(e,n){var t={isOverlappingViewport:function(e,n){void 0===n&&(n=0);var t=window.innerWidth||document.documentElement.clientWidth,c=window.innerHeight||document.documentElement.clientHeight;if(!e.getBoundingClientRect)return!0;var f=e.getBoundingClientRect(),o=f.right-f.left,a=f.bottom-f.top,i=o>0&&a>0&&f.top<c+n&&f.bottom>-n&&f.left<t+n&&f.right>-n;return i}};e.exports=t},6:function(e,n,t){var c=t(7),f={};f.ready=function(e,n){c.ready(e,n)},f.load=function(e,n,t){f.ready(e,function(){var e=0===n.lastIndexOf("//",0);e&&(n=window.location.protocol+n),c(n,t)})},f.done=function(e){c.done(e)},e.exports=f},7:function(e,n){function t(e,n){for(var t=0,c=e.length;c>t;++t)if(!n(e[t]))return v;return!0}function c(e,n){return t(e,function(e){return!n(e)}),v}function f(e,n,a){function i(e){if(e.call){var n=e;return n()}return u[e]}function p(){if(!--g){if(u[w]=1,m){var e=m;e()}for(var n in b)t(n.split("|"),i)&&!c(b[n],i)&&(b[n]=[])}}e=e[h]?e:[e];var v=n&&"function"==typeof n,m=v?n:a,w=v?e.join(""):n,g=e.length;return setTimeout(function(){c(e,function(e){return null===e?p():l[e]?(w&&(r[w]=1),2==l[e]&&p()):(l[e]=1,w&&(r[w]=1),void o(!d.test(e)&&s?s+e+".js":e,p))})},0),f}function o(e,n){var t=!1,c=function(e,n){t||("loaded"===e.readyState||"completed"===e.readyState?n():setTimeout(function(){c(e,n)},100))},f=a.createElement("script"),o=v,d=f.onload=f.onerror=f[y]=function(){t||(t=!0,(!f.readyState||/^c|loade/.test(f.readyState))&&(f.onload=f[y]=null,o=1,l[e]=2,n()))};c(f,d),f.async=1,f.src=e,i.insertBefore(f,i.firstChild)}/*!
         * $script.js Async loader & dependency manager
         * https://github.com/ded/script.js
         * (c) Dustin Diaz 2013
         * License: MIT
         */
            var a=document,i=a.getElementsByTagName("head")[0],d=/^https?:\/\//,u={},r={},b={},s="",l={},p="string",v=!1,h="push",m="DOMContentLoaded",w="readyState",g="addEventListenerName",y="onreadystatechangeName";!a[w]&&a[g]&&(a[g](m,function E(){a.removeEventListener(m,E,v),a[w]="complete"},v),a[w]="loading"),f.get=o,f.order=function(e,n,t){!function c(){var o=e.shift();e.length?f(o,c):f(o,n,t)}()},f.path=function(e){s=e},f.ready=function(e,n,o){e=e[h]?e:[e];var a=[],i=!c(e,function(e){u[e]||a.push(e)})&&t(e,function(e){return u[e]});return i?n():!function(e){if(b[e]||(b[e]=[]),b[e][h](n),o){var t=o;t(a)}}(e.join("|")),f},f.done=function(e){f([null],e)},e.exports=f},8:function(e,n){var t={adjust:function(e,n,t){var c=e*t+n*t,f=window.navigator.userAgent,o=f.indexOf("Mozilla/5.0")>-1&&f.indexOf("Android ")>-1&&f.indexOf("AppleWebKit")>-1&&!(f.indexOf("Chrome")>-1),a=o?"":", user-scalable=no";if(screen.width<c){var i=document.getElementById("viewport");if(i){var d=.01,u=screen.width/c-d;i.setAttribute("content","width="+c+", initial-scale="+u+a)}}}};e.exports=t},9:function(e,n){var t={},c={add:function(e){Object.keys(e).forEach(function(n){t[n]=e[n]})},get:function(e){return t[e]}};e.exports=c},1094:function(e,n,t){function c(e,n){"ar"===e?t.e(28,n):"cs-CZ"===e?t.e(29,n):"da-DK"===e?t.e(30,n):"de"===e?t.e(31,n):"el-GR"===e?t.e(32,n):"en-AU"===e?t.e(33,n):"en-GB"===e?t.e(34,n):"en-IN"===e?t.e(35,n):"es-419"===e?t.e(36,n):"es-ES"===e?t.e(37,n):"fi-FI"===e?t.e(38,n):"fr"===e?t.e(39,n):"he-IL"===e?t.e(40,n):"hi-IN"===e?t.e(41,n):"hu-HU"===e?t.e(42,n):"id"===e?t.e(43,n):"id-ID"===e?t.e(44,n):"it"===e?t.e(45,n):"ja"===e?t.e(46,n):"ko-KR"===e?t.e(47,n):"ms-MY"===e?t.e(48,n):"nb-NO"===e?t.e(49,n):"nl"===e?t.e(50,n):"no"===e?t.e(51,n):"pl-PL"===e?t.e(52,n):"pt-BR"===e?t.e(53,n):"pt-PT"===e?t.e(54,n):"ro-RO"===e?t.e(55,n):"ru-RU"===e?t.e(56,n):"sk-SK"===e?t.e(57,n):"sv-SE"===e?t.e(58,n):"th-TH"===e?t.e(59,n):"tl-PH"===e?t.e(60,n):"tr"===e?t.e(61,n):"uk-UA"===e?t.e(62,n):"ur"===e?t.e(63,n):"vi-VN"===e?t.e(64,n):"zh-CN"===e?t.e(65,n):"zh-HK"===e?t.e(66,n):"zh-TW"===e?t.e(67,n):"en-US"===e?t.e(68,n):n()}function f(e,n){"ar"===e?t.e(28,function(){t(1053),n()}):"cs-CZ"===e?t.e(29,function(){t(1054),n()}):"da-DK"===e?t.e(30,function(){t(1055),n()}):"de"===e?t.e(31,function(){t(1056),n()}):"el-GR"===e?t.e(32,function(){t(1057),n()}):"en-AU"===e?t.e(33,function(){t(1058),n()}):"en-GB"===e?t.e(34,function(){t(1059),n()}):"en-IN"===e?t.e(35,function(){t(1060),n()}):"es-419"===e?t.e(36,function(){t(1061),n()}):"es-ES"===e?t.e(37,function(){t(1062),n()}):"fi-FI"===e?t.e(38,function(){t(1063),n()}):"fr"===e?t.e(39,function(){t(1064),n()}):"he-IL"===e?t.e(40,function(){t(1065),n()}):"hi-IN"===e?t.e(41,function(){t(1066),n()}):"hu-HU"===e?t.e(42,function(){t(1067),n()}):"id"===e?t.e(43,function(){t(1068),n()}):"id-ID"===e?t.e(44,function(){t(1069),n()}):"it"===e?t.e(45,function(){t(1070),n()}):"ja"===e?t.e(46,function(){t(1071),n()}):"ko-KR"===e?t.e(47,function(){t(1072),n()}):"ms-MY"===e?t.e(48,function(){t(1073),n()}):"nb-NO"===e?t.e(49,function(){t(1074),n()}):"nl"===e?t.e(50,function(){t(1075),n()}):"no"===e?t.e(51,function(){t(1076),n()}):"pl-PL"===e?t.e(52,function(){t(1077),n()}):"pt-BR"===e?t.e(53,function(){t(1078),n()}):"pt-PT"===e?t.e(54,function(){t(1079),n()}):"ro-RO"===e?t.e(55,function(){t(1080),n()}):"ru-RU"===e?t.e(56,function(){t(1081),n()}):"sk-SK"===e?t.e(57,function(){t(1082),n()}):"sv-SE"===e?t.e(58,function(){t(1083),n()}):"th-TH"===e?t.e(59,function(){t(1084),n()}):"tl-PH"===e?t.e(60,function(){t(1085),n()}):"tr"===e?t.e(61,function(){t(1086),n()}):"uk-UA"===e?t.e(62,function(){t(1087),n()}):"ur"===e?t.e(63,function(){t(1088),n()}):"vi-VN"===e?t.e(64,function(){t(1089),n()}):"zh-CN"===e?t.e(65,function(){t(1090),n()}):"zh-HK"===e?t.e(66,function(){t(1091),n()}):"zh-TW"===e?t.e(67,function(){t(1092),n()}):"en-US"===e?t.e(68,function(){t(1093),n()}):n()}t(2);var o=window.Pw={},a=function(e){t.e(1,function(){}),t.e(2,function(){}),t.e(5,function(){}),t.e(9,function(){}),t.e(10,function(){}),t.e(70,function(){}),t.e(71,function(){}),t.e(1,function(){t(10),t.e(2,function(){t(16),t.e(5,function(){t(43),t.e(9,function(){t(147),t.e(10,function(){t(148),t.e(70,function(){t(1095),t.e(71,function(){t(155),e()})})})})})})})};o.loadTemplates=function(e){t.e(25,function(){t(1050),e()})},o.loadEverything=function(e,n){c(e,function(){}),a(function(){c(e,function(){f(e,n)})})}}});        Pw.loadEverything(Pc.locale, function(){});
    </script>

</head>
<body>

<div class="App AppBase Module content_only unauth" data-component-type="17" id="App-4">

    <div class="appContent">
        <div class="mainContainer">


            <div class="LoginPage Module" id="LoginPage-1">
                <div class="contents">
                    <div class="Login LoginBase Module" id="Login-5">

                        <form class="standardForm standardForm--login " method="post" action="userRegister.htm" name="form1">

                            <h1>
                                裱花大师                 </h1>

                            <div class="socialLogin">
                                    <p>
                                        <label for="signup_username" class="required">邮箱</label>
                                        <input type="text" id="signup_username" name="userEmail" required="required" onblur="checkReg(0)">
                                        <span id="email"></span>
                                    </p>
                                    <p>
                                        <label for="signup_nickname" class="required">昵称</label>
                                        <input type="text" id="signup_nickname" name="userName" required="required" onblur="checkReg(1)">
                                        <span class="help-inline" id="name"></span>
                                    </p>
                                    <p>
                                        <label class="required">性别</label>
                                    </p><div id="signup_gender" class="reset-labels reset-inputs"><input type="radio" id="signup_gender_0" name="userGender" required="required" value="男" checked="checked"><label for="signup_gender_0" class="required">男</label><input type="radio" id="signup_gender_1" name="userGender" required="required" value="女"><label for="signup_gender_1" class="required">女</label></div>
                                    <p></p>
                                    <p>
                                        <label for="signup_password" class="required">设置密码</label>
                                        <input type="password" id="signup_password" name="userPassword" required="required" onblur="checkPwd()">
                                        <span class="help-inline" id="pwd"></span>
                                    </p>
                                    <p>
                                        <label for="signup_repassword" class="required">确认密码</label>
                                        <input type="password" id="signup_repassword" name="txtRpewd" required="required" onblur="checkRpwd()">
                                        <span class="help-inline" id="Rpwd"></span>
                                    </p>
                            </div>
                        </form>
                            <div class="formFooter">

                                <div class="formFooterButtons">
                                    <button class="Button Module btn hasText large primary rounded" id="Button-9" type="submit" tabIndex="4" onclick="validate()" id="loginOK">

                                        <span class="buttonText">注册</span>
                                    </button>
                                </div>

                                <ul class="auxillaryLinks">
                                    <p>已注册账号？</p>
                                    <li>
                                        <a href="goLoginPage.htm">Log in</a>
                                    </li>
                                </ul>
                            </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="appendedContainer">
        </div>
    </div>

</div>

</body>
</html>