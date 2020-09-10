<template>
  <div class="col-12 float-left" id="reservations_page">
    <div class="col-12 float-left text-center">
      <div class="col-sm-12 float-left text-center">
        <button class="btn btn-sm btn-info" @click="cancel()">Cancel & Refund</button>
      </div>
    </div>

    <form method="POST" style="float:left;margin-top:15px;">
      <div id="reservationForm" class="float-left" style="width:100%;">
        <div class="col-sm-12 float-left formField">
          <label>First Name</label>
          <input type="text" disabled required class="form-control textEntry" name="first_name" v-model="form.first_name" />
        </div>
        <div class="col-sm-12 float-left formField">
          <label>Last Name</label>
          <input type="text" disabled required class="form-control textEntry" name="last_name" v-model="form.last_name" />
        </div>
        <div class="col-12 float-left formField">
          <label>Address</label>
          <input type="text" disabled required class="form-control textEntry" name="address" v-model="form.address" />
        </div>
        <div class="col-sm-12 float-left formField">
          <label>City</label>
          <input type="text" disabled required class="form-control textEntry" name="city" v-model="form.city" />
        </div>
        <div class="col-sm-12 float-left formField">
          <label>State</label>
          <!-- <input type="text" class="form-control textEntry" name="state" v-model="form.state" /> -->
          <select disabled class="form-control textEntry" v-model="form.state">
            <option value="">Select State</option>
            <option v-for="val in states" :value="val">{{val}}</option>
          </select>
        </div>
        <div class="col-sm-12 float-left formField">
          <label>Zip</label>
          <input type="text" disabled class="form-control textEntry" name="zip_code" v-model="form.zip_code" />
        </div>
        <div class="col-sm-12 float-left formField">
          <label>Phone#</label>
          <input type="text" disabled required class="form-control textEntry" name="phone" v-model="form.phone" />
        </div>
        <div class="col-sm-12 float-left formField">
          <label>Email</label>
          <input type="text" disabled required class="form-control textEntry" name="email" v-model="form.email" />
        </div>
        <hr style="width:90%;height:2px;background-color: #e0e0e0;margin-left: 5%;float:left;" />
        <div class="col-sm-12 float-left formField longLabel">
          <label>Date In:</label>
          <input type="text" disabled class="form-control textEntry" v-model="form.reservation_date_start" />
          <!-- <span style="float:right;">{{form.reservation_date_start}}</span> -->
        </div>
        <div class="col-sm-12 float-left formField longLabel">
          <label>Date Out:</label>
          <input type="text" disabled class="form-control textEntry" v-model="form.reservation_date_end" />
        </div>
        <div class="col-12 float-left formField longLabel">
          <label class="longLabel">Length of Stay</label>
          <!-- <input type="date" required class="form-control textEntry" name="arrival_time" v-model="form.arrival_time" /> -->
          <select required disabled class="form-control textEntry" v-model="form.length_of_stay">
            <option v-for="val in stay_options" :value="val">{{val.text}} (${{val.price}})</option>
          </select>
        </div>
        <div class="col-12 float-left">
          <b>Site Types</b><br />
          - Standard (Includes full hook-ups and paved pull-through site.)<br />
          - Deluxe (Includes all standard amenities plus patio, picnic table, and hilltop site. Add $4 per day.)<br />
          - Premium (Includes all standard amenities plus a large patio, large picnic table, and gazebo. Add $19 per day.)<br /><br />
        </div>
        <div class="col-12 float-left formField longLabel">
          <label class="longLabel">Choose Site Type</label>
          <select required disabled class="form-control textEntry" v-model="form.site_type">
            <option v-for="val in site_type_options" :value="val">{{val.text}} (+${{val.price}})</option>
          </select>
        </div>
        <div class="col-12 float-left formField longLabel" v-if="sites_available">
          <label>Choose Site Spot</label>
          <select v-if="sites_available.length" required class="form-control textEntry" v-model="form.site_selected">
            <option value="">Select Site Spot</option>
            <option v-for="val in sites_available" :value="val">#{{val.site_spot_number}}</option>
          </select>
          <div v-else style="float:left;">
            There are not site spots available for the options you selected.
          </div>
        </div>
        <hr style="width:90%;height:2px;background-color: #e0e0e0;margin-left: 5%;float:left;" />
        <div class="col-12 float-left formField longLabel">
          <label class="longLabel">Passport America Member?</label>
          <button class="btn btn-sm" style="color:#000;" disabled :class="form.passport_america_member === 'No' ? 'btn-info' : 'btn-outline-info'" @click="form.passport_america_member = 'No'">No</button>
          <button class="btn btn-sm" style="color:#000;" disabled :class="form.passport_america_member === 'Yes' ? 'btn-info' : 'btn-outline-info'" @click="form.passport_america_member = 'Yes'">Yes</button>
        </div>
        <div class="col-12 float-left formField longLabel" v-if="form.passport_america_member === 'Yes'">
          <label class="longLabel">Membership ID</label>
          <input type="text" required class="form-control textEntry" disabled name="membership_id" v-model="form.membership_id" />
        </div>
        <div class="col-12 float-left formField longLabel">
          <label class="longLabel">Vehicle License #</label>
          <input type="text" required class="form-control textEntry" disabled name="vehicle_license" v-model="form.vehicle_license" />
        </div>
        <div class="col-12 float-left formField longLabel">
          <label class="longLabel">How did you hear about us?</label>
          <select required disabled class="form-control textEntry" v-model="form.hear_about_us">
            <option value="">Select Option</option>
            <option v-for="val in hear_options" :value="val">{{val}}</option>
          </select>
        </div>
        <hr style="width:90%;height:2px;background-color: #e0e0e0;margin-left: 5%;float:left;" />
        <div class="col-12 float-left text-center">
            <!-- Site Price: ${{configureSitePrice()}}<br />
            Internet Service Fee: $1.50<br />
            Tax: {{configureTaxPrice()}}<br />
            Total Price: ${{calculateTotalPrice()}} -->
            <table class="table" style="border-bottom:0px !important;">
              <tbody>
                <tr>
                  <td style="text-align:right;width:200px;border-top:0px !important;">Site Price:</td>
                  <td style="text-align:left;width:200px;border-top:0px !important;">${{configureSitePrice()}}</td>
                </tr>
                <tr v-if="form.passport_america_member === 'Yes' && form.membership_id !== ''">
                  <td style="text-align:right;width:200px;border-top:0px !important;color: red;">Passport Discount:</td>
                  <td style="text-align:left;width:200px;border-top:0px !important;color:red;">- $19.50 (if staying for a day or more)</td>
                </tr>
                <tr>
                  <td style="text-align:right;width:200px;border-top:0px !important;">Internet Service Fee:</td>
                  <td style="text-align:left;width:200px;border-top:0px !important;">$1.50</td>
                </tr>
                <tr>
                  <td style="text-align:right;width:200px;border-top:0px !important;">Tax:</td>
                  <td style="text-align:left;width:200px;border-top:0px !important;">${{configureTaxPrice()}}</td>
                </tr>
                <tr>
                  <td style="text-align:right;width:200px;border-top:0px !important;">Total Price:</td>
                  <td style="text-align:left;width:200px;border-top:0px !important;">${{calculateTotalPrice()}}</td>
                </tr>
              </tbody>
            </table>
          </div>
      </div>
    </form>

    <div v-if="cancelLoading" class="background" style="color:#fff;">
      <small-loader></small-loader>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import VueTimepicker from 'vue2-timepicker'
import DatePicker from 'vue2-datepicker';
var methods = {}

methods.cancel = function () {
  this.cancelLoading = true
  Swal.fire({
    title: 'Are you sure?',
    text: "You want to cancel this reservation and refund them the money?",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, Cancel!'
  }).then((result) => {
    console.log(result)
    if (result.value) {
      const postData = {
        id: this.form.id
      }
      console.log(postData)
      axios.post('/api/cancelReservationRefund', postData).then(response => {
        if (response.status === 200) {
          if (response.data === 'success-refund') {
            Swal.fire(
              'Cancelled!',
              'This reservation has been cancelled and the money has been refunded.',
              'success'
            )
          } else if (response.data === 'success-cancel') {
            Swal.fire(
              'Cancelled!',
              'This reservation has been cancelled but you will have to refund this through actual PayPal.',
              'success'
            )
          }
          this.cancelLoading = false
          this.$emit('canceled')
        }
      }).catch(error => {
        console.log(error)
        this.cancelLoading = false
        Swal.fire(
          'Oops!',
          'Something messed up when trying to cancel. If it keeps happening please contact ac_080@outlook.com',
          'error'
        )
      })
    } else {
      this.cancelLoading = false
    }
  })
}

methods.configureForm = function () {
  this.form = {
    id: this.reservation.id,
    first_name: this.reservation.first_name,
    last_name: this.reservation.last_name,
    address: this.reservation.address,
    city: this.reservation.city,
    state: this.reservation.state,
    zip_code: this.reservation.zip_code,
    phone: this.reservation.phone,
    email: this.reservation.email,
    date_in: this.reservation.date_in,
    arrival_time: this.reservation.arrival_time,
    length_of_stay: {text: this.reservation.length_of_stay_text, price: this.reservation.length_of_stay_price, days: this.reservation.length_of_stay_days},
    passport_america_member: this.reservation.passport_america_member,
    membership_id: this.reservation.membership_id,
    site_type: {text: this.reservation.site_type_text, price: this.reservation.site_type_price},
    vehicle_license: this.reservation.vehicle_license,
    hear_about_us: this.reservation.hear_about_us,
    site_selected:'',
    reservation_date_start: this.reservation.reservation_date_start,
    reservation_date_end: this.reservation.reservation_date_end,
    total_price: this.reservation.total_price,
    tax: this.reservation.tax,
    length_of_stay_price: this.reservation.length_of_stay_price,
    internet_service_fee: this.reservation.internet_service_fee
  }
}

methods.clearForm = function () {
  this.form = {
        id: '',
        first_name: '',
        last_name: '',
        address: '',
        city: '',
        state: '',
        zip_code: '',
        phone: '',
        email: '',
        date_in: '',
        arrival_time: '',
        length_of_stay: {text: '1 Day', price: 39, days: 1},
        passport_america_member: 'No',
        membership_id: '',
        site_type: {text: 'Standard', price: 0},
        vehicle_license: '',
        hear_about_us: '',
        site_selected: ''
      }
  this.arrival_hour = ''
  this.arrival_minutes = '00'
  this.ampm = 'AM'
}

methods.configureShow = function () {
  var arr = ['first_name', 'last_name', 'address', 'city', 'state', 'zip_code', 'phone', 'email', 'date_in', 'site_selected', 'vehicle_license']
  var ready = true
  var that = this
  arr.forEach(function (val) {
    if (that.form[val] === '' || !that.form[val]) {
      ready = false
    }
  })
  console.log(ready)
  return ready
}

methods.paymentCancelled = function (e) {
  console.log('payment cancelled')
  console.log(e)
}

methods.paymentCompleted = function (e) {
  console.log(e)
  var id = e.transactions[0].related_resources[0].sale.id
  this.submitForm(id)
}

methods.configureSitePrice = function () {
  var calc = 0
  calc = parseFloat(this.form.length_of_stay.price) + (parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days))
  return parseFloat(calc).toFixed(2)
}

methods.calculateTotalPrice = function () {
  var calc = 0
  if (this.form.passport_america_member === 'Yes' && this.form.membership_id !== '') {
    calc = parseFloat(parseFloat(this.form.length_of_stay.price) + parseFloat(parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days)))
    calc = parseFloat(calc - 19.50)
    calc += 1.50
  } else {
    calc = parseFloat(parseFloat(this.form.length_of_stay.price) + parseFloat(parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days)))
    calc += 1.50
  }
  console.log(calc)
  var tax = parseFloat(parseFloat(calc) * .0885).toFixed(2)
  calc += parseFloat(tax)
  calc = calc.toFixed(2)
  console.log(calc)
  return calc
}

methods.configureTaxPrice = function () {
  var calc = 0
  if (this.form.passport_america_member === 'Yes' && this.form.membership_id !== '') {
    calc = parseFloat(this.form.length_of_stay.price) + (parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days))
    calc = calc - 19.50
    calc += 1.50
  } else {
    calc = parseFloat(this.form.length_of_stay.price) + (parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days))
    calc += 1.50
  }
  var tax = parseFloat(parseFloat(calc) * .0885).toFixed(2)
  return tax
}

methods.checkReservations = function () {
  this.reservationLoading = true
  var arrivalHour = this.arrival_hour
  if (this.ampm === 'PM') {
    if (this.arrival_hour === '12') {
      arrivalHour = '00'
    } else {
      arrivalHour = parseInt(arrivalHour) + 12
    }
  }
  arrivalHour = '0' + arrivalHour
  arrivalHour = arrivalHour.substr(-2)
  const postData = {
    arrival_hour: arrivalHour,
    arrival_minutes: this.arrival_minutes,
    ampm: this.ampm,
    date_in: this.form.date_in,
    stay_options: this.form.length_of_stay,
    site_id: 1,
    site_type: this.form.site_type
  }
  console.log(postData)
  axios.post('/api/checkReservations', postData).then(response => {
    if (response.status) {
      console.log(response)
      this.sites_available = response.data.r
      this.reservationLoading = false
    }
  })
}

export default {
  name: 'reservations_view_page',
  components: {
    VueTimepicker,
    DatePicker
  },
  watch: {
    'form.date_in': function () {
      if (this.arrival_hour !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    arrival_hour: function () {
      if (this.form.date_in !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    arrival_minutes: function () {
      if (this.form.date_in !== '' && this.arrival_hour !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    ampm: function () {
      if (this.form.date_in !== '' && this.arrival_hour !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    'form.length_of_stay': function () {
      if (this.arrival_hour !== '' && this.form.date_in !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    'form.site_type': function () {
      if (this.arrival_hour !== '' && this.form.date_in !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    }
  },
  props: ['reservation'],
  data () {
    return {
      cancelLoading: false,
      form: {
        first_name: '',
        last_name: '',
        address: '',
        city: '',
        state: '',
        zip_code: '',
        phone: '',
        email: '',
        date_in: '',
        arrival_time: '',
        length_of_stay: {text: '1 Day', price: 39, days: 1},
        passport_america_member: 'No',
        membership_id: '',
        site_type: {text: 'Standard', price: 0},
        vehicle_license: '',
        hear_about_us: '',
        site_selected: ''
      },
      reservationLoading: false,
      sites_available: null,
      hear_options: [
        'Extending Stay',
        'Billboard',
        'Google',
        'Facebook',
        'Passing By',
        'Word of Mouth',
        'Referral'
      ],
      arrival_hour: '',
      arrival_minutes: '00',
      ampm: 'AM',
      site_type_options: [
        {text: 'Standard', price: 0},
        {text: 'Deluxe', price: 4},
        {text: 'Premium', price: 19}
      ],
      states: [
        "Alabama",
        "Alaska",
        "Alberta",
        "Arizona",
        "Arkansas",
        "British Columbia",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Manitoba",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Brunswick",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "Newfoundland and Labrador",
        "North Carolina",
        "North Dakota",
        "Northwest Territories",
        "Nova Scotia",
        "Nunavut",
        "Ohio",
        "Oklahoma",
        "Ontario",
        "Oregon",
        "Pennsylvania",
        "Prince Edward Island",
        "Quebec",
        "Rhode Island",
        "Saskatchewan",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming",
        "Yukon"
      ],
      stay_options: [
        {text: "1 Day", price: 39, days: 1},
        {text: "2 Days", price: 70, days: 2},
        {text: "3 Days", price: 105, days: 3},
        {text: "4 Days", price: 140, days: 4},
        {text: "5 Days", price: 175, days: 5},
        {text: "6 Days", price: 210, days: 6},
        {text: "7 Days", price: 224, days: 7},
        {text: "8 Days", price: 256, days: 8},
        {text: "9 Days", price: 288, days: 9},
        {text: "10 Days", price: 320, days: 10},
        {text: "11 Days", price: 352, days: 11},
        {text: "12 Days", price: 384, days: 12},
        {text: "13 Days", price: 416, days: 13},
        {text: "14 Days", price: 448, days: 14},
        {text: "15 Days", price: 480, days: 15},
        {text: "16 Days", price: 512, days: 16},
        {text: "17 Days", price: 544, days: 17},
        {text: "18 Days", price: 576, days: 18},
        {text: "19 Days", price: 608, days: 19},
        {text: "20 Days", price: 640, days: 20},
        {text: "21 Days", price: 672, days: 21},
        {text: "22 Days", price: 704, days: 22},
        {text: "23 Days", price: 736, days: 23},
        {text: "24 Days", price: 768, days: 24},
        {text: "25 Days", price: 800, days: 25},
        {text: "26 Days", price: 832, days: 26},
        {text: "27 Days", price: 864, days: 27},
        {text: "28 Days", price: 896, days: 28},
        {text: "10 Hours", price: 20, days: 1},
        {text: "11 Hours", price: 22, days: 1},
        {text: "12 Hours", price: 24, days: 1},
        {text: "13 Hours", price: 26, days: 1},
        {text: "14 Hours", price: 28, days: 1},
        {text: "15 Hours", price: 30, days: 1},
        {text: "16 Hours", price: 32, days: 1},
        {text: "17 Hours", price: 34, days: 1},
        {text: "18 Hours", price: 36, days: 1},
        {text: "19 Hours", price: 38, days: 1},
      ]
    }
  },
  methods: methods,
  mounted: function () {
    // console.log('mounted')
    // axios.post('http://rv_server.test/api/getReservations').then(response => {
    //   console.log(response)
    // })
    this.configureForm()
  }
}
</script>

<style lang="scss" scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.background {
        position: fixed;
        top:0;left:0;right:0;bottom:0;
        width:100%;
        height:100%;
        z-index: 100;
        background-color: rgba(0,0,0,.4);

        h4 {
          width:100%;float:left;
          text-align:center;
          margin-top:50px;
        }
}

.formField {

  &.longLabel {
    label {
      width: 250px !important;
    }
    .textEntry {
      width: calc(100% - 250px);
    }
    div {
      width: calc(100% - 250px);
    }
    @media (max-width: 600px) {
      .textEntry {
        width: 100%;float:left;
      }
      div {
        width:100%;float:left;
      }
    }
  }

  @media (max-width: 600px) {
    label {
      margin-bottom: 0px !important;
    }
    .textEntry {
      width: 100% !important;float:left;
    }
    div {
      width:100%;float:left;
    }
  }

  label {
    float: left;
    width:120px;
    line-height:40px;
    text-align:left;
    padding-right: 10px;
  }
  .textEntry {
    width: calc(100% - 120px);
  }
  .timeField {
    width: 100px;
    float:left;
  }
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
