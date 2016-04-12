package ntv.upgrade.superleaguemaster.AppConstants;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import  ntv.upgrade.superleaguemaster.R;

/**
 * Created by Jose on 3/15/2015.
 */
public class AppConstant {

    public static List<String> availableTourneys = Arrays.asList(
            "Copa Garrincha","LDF", "Copa CoCa Cola"
    );



   public static String[][] mTeamArrayList = {
           {
                   "0", "Atletico Pantoja", "Olimpico Felix Sanchez" , "PROFILE"
           },
           {
                   "1", "O&M FC", "Olimpico Felix Sanchez" , "PROFILE"
           },
           {
                   "2", "Moca FC", " Bragana Garcia, Moca" , "PROFILE"
           },
           {
                   "3", "Cibao FC", "Estadio Cibao FC" , "PROFILE"
           },
           {
                   "4", "Barcelona Atletico", "Olimpico Felix Sanchez" , "PROFILE"
           },
           {
                   "5", "Delfines del Este FC", "Estadio Francisco Micheli" , "PROFILE"
           },
           {
                   "6", "Atletico San Cristobal", "Panamericano, San Cristobal " , "PROFILE"
           },
           {
                   "7", "Atlantico FC", "Estadio Leonel Placido " , "PROFILE"
           },
           {
                   "8", "Atletico Vega Real", "Estadio Olimpico, La Vega " , "PROFILE"
           },
           {
                   "9",  "Bauger FC", "Olimpico Felix Sanchez " , "PROFILE"
           },
           {
                   "10", "Romana FC", "Estadio Francisco Micheli " , "PROFILE"
           },
           {
                   "11", "TBD", "TBD " , "PROFILE"
           }

    };

        public static String[][] mTeamPlayerArrayList = {
                {
                        "2",  "Josean Acevedo", "00", "Defenza", "Vegueta", "Primera"
                },
                {
                        "1", "player1", "21", "Delantero", "MangaTuGoal", "Primera"
                },
                {
                        "2",  "Jose Frometa", "21", "Portero", "Vacota", "Primera"
                },
                {
                        "2",  "Elias Serulle", "69", "OneKidArmy", "Perullo", "Primera"
                },
                {
                        "2",  "Paulino Gomez", "-07", "Delantero", "Kamikaze", "Primera"
                },
                {
                        "2",  "Rosendo Yanez", "64", "Delantero", "WarFatSo", "Primera"
                },
                {
                        "2",  "El Chino", "666", "GodMode", "Flancy Lover", "Primera"
                },
                {
                        "2",  "Flancy", "777", "Defenza", "MangaTuGoal", "Primera"
                },

                {
                        "2", "Ronald Kohn", "09","Centro", "MonoArana", "Primera"
                },
                {
                        "3", "player3", "73", "Centro", "MangaTuGoal", "Primera"
                },
                {
                        "4",  "player4", "44","Delantero", "MangaTuGoal", "Primera"
                },
                {
                        "5",  "player5", "05","Delantero", "MangaTuGoal", "Primera"
                },
                {
                        "6",  "player6", "86", "Defenza", "MangaTuGoal", "Primera"
                },
                {
                        "7",  "player7", "107", "Defenza", "MangaTuGoal", "Primera"
                },
                {
                        "8",  "player8", "68", "Defenza", "MangaTuGoal", "Primera"
                },
                {
                        "9",  "player9", "39","Centro", "MangaTuGoal", "Primera"
                },
                {
                        "10",  "player10", "10", "Centro", "MangaTuGoal", "Primera"
                }
        };

        public static int[][][] mMatchArrayList = {
                {
                        {0 , 6 , 10 },{0 , 3 , 8 },{0 , 2 , 9}, { 0 , 4 , 7},{ 0 , 0 , 1}
                },

                {
                        {1 , 3 , 0  },{1 , 7 , 6},{1 , 8 , 2}, {1 , 10 , 1},{1 , 9 , 4 }
                },
                {
                        {2 , 6 , 9 },{2 , 2 , 0},{2 , 10 , 7}, { 2 , 1 , 3 },{2 , 4 , 8}
                },
                {
                        {3 , 4 , 0},{3 , 9 , 10 },{3 , 8 , 6}, { 3 , 1 , 7 },{ 3 , 3 , 2 }
                },

                {
                        {4 , 7 , 9},{4 , 4, 3 },{4 , 8 , 10}, {4 , 0 , 6},{4 , 2 , 1 }
                },
                {
                        {5 , 0 , 10},{5 , 8, 7},{5 , 3 , 6}, { 5 , 2 , 4 },{ 5 , 1 , 9}
                },
                {
                        {6 , 1 , 4 },{6 , 6, 2 },{6 , 9 , 8}, { 6 , 7 , 0 },{6 , 10 , 3}
                },

                {
                        {7 , 0 , 9 },{7 , 3, 7},{7 , 4 , 6 }, {7 , 2 , 10 },{ 7 , 1 ,8 }
                },
                {
                        {8 , 6 , 1},{8, 10, 4 },{8, 9, 3 }, {8, 7 , 2 },{8, 8,0 }
                },
                {
                        {9 , 7 , 4 },{9, 1, 0 },{9, 10, 6 }, {9, 9 , 2 },{9, 8, 3 }
                },
                {
                        {10 , 1 , 10 },{10, 6, 7},{10, 2, 8}, {10, 4 , 9 },{10, 0, 3 }
                },
                {
                        {11 , 3 , 1 },{11, 0, 2 },{11, 9, 6}, {11, 7 , 10 },{11, 8, 4 }
                },
                {
                        {12 , 0 , 4 },{12, 10, 9 },{12, 6, 8}, {12, 7 , 1 },{12, 2, 3 }
                }

        };


        public static String[][][] mMatchTimeDateArray = {
        {//0
                { "Domingo 08" , "04:00 P.M.", "3" },{ "Domingo 08" , "04:00 P.M.", "3" },
                { "Domingo 08" , "04:00 P.M.", "3" }, {"Domingo 08" , "04:00 P.M.", "3" },
                { "Domingo 08" , "04:00 P.M.", "3"}
        },

        {//1
                { "Sabado 14" , "04:00 P.M.", "3" },{ "Sabado 14" , "04:00 P.M.", "3" },
                { "Domingo 15" , "04:00 P.M.", "3" }, {"Domingo 15" , "04:00 P.M.", "3" },
                { "Domingo 15" , "04:00 P.M.", "3"}
        },
        {//2
                { "Sabado 21" , "04:00 P.M.", "3" },{ "Sabado 21" , "04:00 P.M.", "3" },
                { "Domingo 22" , "04:00 P.M.", "3" }, {"Domingo 22" , "04:00 P.M.", "3" },
                { "Domingo 22" , "04:00 P.M.", "3"}
        },
        {//3
                { "Sabado 28" , "04:00 P.M.", "3" },{ "Sabado 28" , "04:00 P.M.", "3" },
                { "Domingo 29" , "04:00 P.M.", "3" }, {"Domingo 29" , "04:00 P.M.", "3" },
                { "Domingo 29" , "04:00 P.M.", "3"}
        },

        {//4
                { "Sabado 11" , "04:00 P.M.", "3" },{ "Domingo 12" , "04:00 P.M.", "3" },
                { "Domingo 12" , "04:00 P.M.", "3" }, {"Domingo 12" , "04:00 P.M.", "3" },
                { "Domingo 12" , "04:00 P.M.", "3"}
        },
        {//5
                { "Sabado 18" , "04:00 P.M.", "4" },{ "Domingo 19" , "04:00 P.M.", "4" },
                { "Domingo 19" , "04:00 P.M.", "4" }, {"Domingo 19" , "04:00 P.M.", "4" },
                { "Domingo 19" , "04:00 P.M.", "4"}
        },
        {//6
                { "Sabado 25" , "04:00 P.M.", "4" },{ "Domingo 26 " , "04:00 P.M.", "4" },
                { "Domingo 26" , "04:00 P.M.", "4" }, {"Domingo 26" , "04:00 P.M.", "4" },
                { "Domingo 26" , "04:00 P.M.", "4"}
        },

        {//7
                { "Sabado 02" , "04:00 P.M.", "5" },{ "Sabado 02" , "04:00 P.M.", "5" },
                { "Domingo 03" , "04:00 P.M.", "5" }, {"Domingo 03" , "04:00 P.M.", "5" },
                { "Domingo 03" , "04:00 P.M.", "5"}
        },
        {//8
                { "Sabado 09" , "04:00 P.M.", "5" },{ "Sabado 09" , "04:00 P.M.", "5" },
                { "Domingo 10" , "04:00 P.M.", "5" }, {"Domingo 10" , "04:00 P.M.", "5" },
                { "Domingo 10" , "04:00 P.M.", "5"}
        },
        {//9
                { "Sabado 16" , "04:00 P.M.", "5" },{ "Sabado 16" , "04:00 P.M.", "5" },
                { "Domingo 17" , "04:00 P.M.", "5" }, {"Domingo 17" , "04:00 P.M.", "5" },
                { "Domingo 17" , "04:00 P.M.", "5"}
        },
        {//10
                { "Sabado 23" , "04:00 P.M.", "5" },{ "Sabado 23" , "04:00 P.M.", "5" },
                { "Domingo 24" , "04:00 P.M.", "5" }, {"Domingo 24" , "04:00 P.M.", "5" },
                { "Domingo 24" , "04:00 P.M.", "5"}
        },
        {//11
                { "Sabado 30" , "04:00 P.M.", "5" },{ "Sabado 30" , "04:00 P.M.", "5" },
                { "Domingo 31" , "04:00 P.M.", "5" }, {"Domingo 31" , "04:00 P.M.", "5" },
                { "Domingo 31" , "04:00 P.M.", "5"}
        },
        {//12
                { "Jueves 04" , "04:00 P.M.", "6" },{ "Jueves 04" , "04:00 P.M.", "6" },
                { "Jueves 04" , "04:00 P.M.", "6" }, {"Jueves 04" , "04:00 P.M.", "6" },
                { "Jueves 04" , "04:00 P.M.", "6"}
        },
        {//13
                { "Sabado 20" , "04:00 P.M.", "6" },{ "Sabado 20" , "04:00 P.M.", "6" },
                { "Domingo 21" , "04:00 P.M.", "6" }, {"Domingo 21" , "04:00 P.M.", "6" },
                { "Domingo 21" , "04:00 P.M.", "6"}
        },
        {//14
                { "Sabado 27" , "04:00 P.M.", "6" },{ "Domingo 28" , "04:00 P.M.", "6" },
                { "Domingo 28" , "04:00 P.M.", "6" }, {"Domingo 28" , "04:00 P.M.", "6" },
                { "Domingo 21" , "04:00 P.M.", "6"}
        },
        {//15
                { "Sabado 04" , "04:00 P.M.", "7" },{ "Sabado 04" , "04:00 P.M.", "7" },
                { "Domingo 05" , "04:00 P.M.", "7" }, {"Domingo 05" , "04:00 P.M.", "7" },
                { "Domingo 05" , "04:00 P.M.", "7"}
        },
        {//16
                { "Sabado 11" , "04:00 P.M.", "7" },{ "Sabado 11" , "04:00 P.M.", "7" },
                { "Domingo 12" , "04:00 P.M.", "7" }, {"Domingo 12" , "04:00 P.M.", "7" },
                { "Domingo 12" , "04:00 P.M.", "7"}
        },
        {//17
                { "Sabado 18" , "04:00 P.M.", "7" },{ "Sabado 18" , "04:00 P.M.", "7" },
                { "Domingo 19" , "04:00 P.M.", "7" }, {"Domingo 19" , "04:00 P.M.", "7" },
                { "Domingo 19" , "04:00 P.M.", "7"}
        },
        {//18
                { "Sabado 25" , "04:00 P.M.", "7" },  { "Domingo 26" , "04:00 P.M.", "7" }
        },
        {//19
                { "Sabado 01" , "04:00 P.M.", "8" },  { "Domingo 02" , "04:00 P.M.", "8" }
        },
        {//20
                { "Sabado 09" , "04:00 P.M.", "8" },  { "Domingo 10" , "04:00 P.M.", "8" }
        }

        };

        public static String[][][] mMatchTimeDateArray1 = {

                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "13/04"},{ "34/04"},{ "24/04"}
                },
                {
                        { "33/04"},{ "34/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
                {
                        { "23/04"},{ "24/04"},{ "24/04"}
                },
        };



        public static int[] mImageList = {
                R.mipmap.pantojatransparent_copy ,
                R.mipmap.oym_transparent_new_copy,
                R.mipmap.moca_fc_e14239432361701_copy,
                R.mipmap.cibao_futbol_club_e1423943315771_copy,
                R.mipmap.clubbarcelonatransparent_copy,
                R.mipmap.delfinesdeleste1_copy,
                R.mipmap.a_logo_copy,
                R.mipmap.atlantico_transparten_copy,
                R.mipmap.atletico_vega_real1_copy,
                R.mipmap.baugerfc2_copy,
                R.mipmap.delfinesdeleste1_copy,
                R.mipmap.ic_launcher,
                };

        }





