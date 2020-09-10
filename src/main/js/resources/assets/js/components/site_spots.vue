<template>
    <div class="col-12 float-left" id="siteSpots">
        <card title="Sites" :have_carot="true" from="site_spots">
            <div style="width:100%;float:left;" v-if="site_spots">
                <div class="col-sm-12 col-md-4 col-lg-3 float-left mt-3" v-for="value in site_spots">
                    <div class="spotWrapper">
                        <label class="spotName">{{value.spot_name}}</label>

                        <div style="width:100%;float:left;height:200px;">
                            <label class="upcomingReservations">Reservations</label>
                            <table class="table">
                                <thead style="font-size:11px;">
                                    <tr>
                                        <th style="padding:2px;">Start</th>
                                        <th style="padding:2px;">End</th>
                                    </tr>
                                </thead>
                                <tbody v-if="value.reservations.length" style="font-size:10px;">
                                    <tr @click="viewReservation = val" v-for="val in value.reservations">
                                        <td>{{configureDate(val.reservation_date_start)}}</td>
                                        <td>{{configureDate(val.reservation_date_end)}}</td>
                                    </tr>
                                </tbody>
                                <tbody v-else>
                                    <tr style="font-size:10px;">
                                        <td colspan="2" style="text-align:center;color:red;">No Upcoming Reservations</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div style="width:100%;float:left;text-align:center;padding:5px;">
                            <button class="btn btn-sm" :class="value.parking_sensor ? 'btn-success' : 'btn-outline-success'">
                                <i class="fa fa-car"></i>
                            </button>
                            <button class="btn btn-sm" :class="parseInt(value.utility.water) ? 'btn-info' : 'btn-outline-info'">
                                <i class="fa fa-tint"></i>
                            </button>
                            <button class="btn btn-sm" :class="parseInt(value.utility.electric) ? 'btn-warning' : 'btn-outline-warning'">
                                <i class="fa fa-plug"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <small-loader v-else></small-loader>
        </card>
        <div class="myModal" v-if="viewReservation">
            <div class="background" @click="viewReservation = null"></div>
            <div id="reservationHolder">
                <div @click="viewReservation = null" style="background-color:#e0e0e0;position:absolute;right:0px;top:0px;cursor:pointer;width:30px;height:30px;text-align:center;">X</div>
                <reservation-view-page :reservation="viewReservation"></reservation-view-page>
            </div>
        </div>
    </div>
</template>
<script>
import ReservationViewPage from './../pages/reservation_view_page'
// var moment = require('moment');
var methods = {}

methods.getSites = function () {
    const postData = {
        site_id: this.site_id
    }
    axios.post('/api/getSiteSpots', postData).then(response => {
        if (response.status === 200 || response.status === 201) {
            this.site_spots = response.data
            console.log(response.data)
        }
    })
}

methods.configureDate = function (date) {
    var d = moment(date, "YYYY-MM-DD HH:mm:ss").format('MM-DD hh:mm a')
    console.log(d)
    return d
}

export default {
    name: "SiteSpots",
    props: ['site_id'],
    data: function () {
    	return {
            site_spots: null,
            viewReservation: null
    	}
    },
    components: {
        ReservationViewPage
    },
    methods: methods,
    mounted: function() {
        this.getSites()
    },
    destroyed: function() {

    }
}
</script>
<style lang="scss" scoped>
#siteSpots {
    .spotWrapper {
        width:100%;
        float: left;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #ededed;

        .upcomingReservations {
            font-size:.8em;

        }

        .spotName {
            text-decoration:underline;
            font-size:1.2em;
        }
    }

    .myModal {
        position: fixed;
        top:0;left:0;right:0;bottom:0;
        width:100%;
        height:100%;
        z-index: 100;

        .background {
            position:absolute;
            width:100%;
            height:100%;
            background-color: rgba(0,0,0,.3);
        }


        #reservationHolder {
            padding:15px;
            position: absolute;
            width:85%;
            margin-left:7.5%;margin-right:7.5%;
            height:90vh;
            margin-top: 5vh;
            margin-bottom: 5vh;
            background-color: #fff;
            overflow: scroll;
        }

    }
}
</style>
