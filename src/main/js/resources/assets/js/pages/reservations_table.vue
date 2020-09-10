<template>
    <div class="col-12 float-left mb-2">
        <card title="Reservations Table" :have_carot="true" from="reservations_table" class="mt-2">
            <div class="col-sm-12 col-md-5 text-center float-left">
                Start Date<br />
                <input type="date" class="form-control" v-model="start_date" />
            </div>
            <div class="col-sm-12 col-md-5 text-center float-left">
                End Date<br />
                <input type="date" class="form-control" v-model="end_date" />
            </div>
            <div class="col-sm-12 col-md-2 float-left text-center">
                <br />
                <button class="btn btn-info btn-sm" @click="getReservations()">Submit</button>
            </div>
            <div class="col-12 float-left text-center p-3">
                Click on row to view reservation.
            </div>
            <div style="width:100%;float:left;overflow-x: scroll" v-if="!loading">
                <table class="table table-striped" v-if="data">
                    <thead>
                        <tr>
                            <th style="text-align:center;">Invoice ID</th>
                            <th style="text-align:center;">Name</th>
                            <th style="text-align:center;">Spot ID</th>
                            <th style="text-align:center;">Length of Stay</th>
                            <th style="text-align:center;">Arrive Date</th>
                            <th style="text-align:center;">Depart Date</th>
                            <th style="text-align:center;">Price</th>
                            <th style="text-align:center;">Status</th>                            
                        </tr>
                    </thead>
                    <tbody v-if="data && data.length">
                        <tr v-for="value in data" @click="viewReservation = value" :style="value.cancelled === 1 ? 'background-color: #ff928a' : ''">
                            <td style="text-align:center;">{{value.id}}</td>
                            <td style="text-align:center;">{{value.first_name}} {{value.last_name}}</td>
                            <td style="text-align:center;">{{value.site_spot_id}}</td>
                            <td style="text-align:center;">{{value.length_of_stay_text}}</td>
                            <td style="text-align:center;">{{configureDate(value.reservation_date_start)}}</td>
                            <td style="text-align:center;">{{configureDate(value.reservation_date_end)}}</td>
                            <td style="text-align:center;">${{value.total_price}}</td>
                            <td style="text-align:center;">{{value.cancelled === 0 ? 'Booked' : 'Canceled'}}</td>
                        </tr>
                    </tbody>
                    <tbody v-else-if="!data.length">
                        <tr>
                            <td colspan="8" style="text-align:center;">No reservations between these dates.</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div style="width:100%;float:left;text-align:center;" v-else-if="loading">
                <small-loader></small-loader>
            </div>

            <div class="myModal" v-if="viewReservation">
                <div class="background" @click="viewReservation = null"></div>
                <div id="reservationHolder">
                    <div @click="viewReservation = null" style="background-color:red;position:absolute;right:10px;top:5px;cursor:pointer;width:25px;height:25px;text-align:center;border-radius:5px;color:#fff;z-index:1;">X</div>
                    <reservation-view-page v-on:canceled="getReservations()" :reservation="viewReservation"></reservation-view-page>
                </div>
            </div>
        </card>
    </div>
</template>
<script>
import ReservationViewPage from './reservation_view_page'
// var moment = require('moment');
var methods = {}

methods.getReservations = function () {
    this.loading = true
    const postData = {
        startDate: this.start_date,
        endDate: this.end_date
    }
    axios.post('/api/getReservationsTable', postData).then(response => {
        if (response.status === 200 || response.status === 201) {
            console.log(response)
            this.data = response.data
            this.loading = false
        }
    })
}

methods.configureDate = function (date) {
    var d = moment(date, "YYYY-MM-DD HH:mm:ss").format('MM-DD hh:mm a')
    console.log(d)
    return d
}

export default {
    // ===Component name
    name: "ReservationsTable",
    // ===Props passed to component
    props: {},
    // ===Components used by this component
    components: {
        ReservationViewPage
    },
    middleware: 'auth',
    // ====component Data properties
    data(){
        return{
            data: null,
            viewReservation: null,
            start_date: '2020-07-01',
            end_date: '2020-07-15',
            loading: false
        }
    },
    // ===Code to be executed when Component is mounted
    mounted() {
        this.start_date = moment().subtract(1, 'days').format('YYYY-MM-DD')
        this.end_date = moment().add(1, 'days').format('YYYY-MM-DD')
        this.getReservations()
    },
    // ===Computed properties for the component
    computed: {},
    // ===Component methods
    methods: methods
}
</script>
<!-- styles -->
<!-- adding scoped attribute will apply the css to this component only -->
<style scoped lang="scss">
    .table {
        font-size:.8em !important;
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
</style>
