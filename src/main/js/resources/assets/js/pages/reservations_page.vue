<template>
  <div class="col-12 float-left" id="reservations_page">
    <card style="margin-top:15px;text-align:center;">
      <a class="btn btn-sm btn-info" style="color:#fff;" href="https://rvselfpark.com/"><i class="fa fa-arrow-left"></i> Return to Site</a>
    </card>
    <card style="margin-top:15px;text-align:center;">
      <img src="./../../images/rv_map.png" style="max-width:700px;width:100%;margin-left: auto;margin-right:auto;" />
    </card>
    <card style="margin-top:15px;margin-bottom:30px;">
      <form method="POST" @submit.prevent="">
        <div id="reservationForm" class="float-left" style="width:100%;">
          <div class="col-sm-12 float-left formField" :class="configureEntry('first_name')">
            <label>First Name</label>
            <input type="text" required class="form-control textEntry" name="first_name" v-model="form.first_name" />
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('last_name')">
            <label>Last Name</label>
            <input type="text" required class="form-control textEntry" name="last_name" v-model="form.last_name" />
          </div>
          <div class="col-12 float-left formField" :class="configureEntry('address')">
            <label>Address</label>
            <input type="text" required class="form-control textEntry" name="address" v-model="form.address" />
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('city')">
            <label>City</label>
            <input type="text" required class="form-control textEntry" name="city" v-model="form.city" />
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('state')">
            <label>State</label>
            <!-- <input type="text" class="form-control textEntry" name="state" v-model="form.state" /> -->
            <select class="form-control textEntry" v-model="form.state">
              <option value="">Select State</option>
              <option v-for="val in states" :value="val">{{val}}</option>
            </select>
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('zip_code')">
            <label>Zip</label>
            <input type="text" class="form-control textEntry" name="zip_code" v-model="form.zip_code" />
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('phone')">
            <label>Phone#</label>
            <input type="text" required class="form-control textEntry" name="phone" v-model="form.phone" />
          </div>
          <div class="col-sm-12 float-left formField" :class="configureEntry('email')">
            <label>Email</label>
            <input type="text" required class="form-control textEntry" name="email" v-model="form.email" />
          </div>
          <hr style="width:90%;height:2px;background-color: #e0e0e0;margin-left: 5%;float:left;" />
          <div class="col-sm-12 float-left formField longLabel" :class="configureEntry('date_in')">
            <label>Date In</label>
            <!-- <input type="date" required class="form-control textEntry" name="date_in" v-model="form.date_in" /> -->
            <date-picker style="max-width:250px;" v-model="form.date_in" type="date" format="YYYY-MM-DD" valueType="format"></date-picker>
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('arrival_time')">
            <label>Arrival Time</label>
            <!-- <input type="date" required class="form-control textEntry" name="arrival_time" v-model="form.arrival_time" /> -->
            <!-- <select required class="form-control timeField" v-model="arrival_hour">
              <option value="">---</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>
            <select required class="form-control timeField" v-model="arrival_minutes">
              <option value="00">00</option>
              <option value="15">15</option>
              <option value="30">30</option>
              <option value="45">45</option>
            </select>
            <select required class="form-control timeField" v-model="ampm">
              <option value="AM">AM</option>
              <option value="PM">PM</option>
            </select> -->
            <!-- <vue-timepicker v-model="arrival_time_temp" :minute-interval="15" format="hh:mm A"></vue-timepicker> -->
            <select class="form-control" style="max-width:250px;" v-model="arrival_time_temp">
              <option value="">Select Time</option>
              <option value="12:00 PM">12:00 PM</option>
              <option value="12:30 PM">12:30 PM</option>
              <option value="01:00 PM">01:00 PM</option>
              <option value="01:30 PM">01:30 PM</option>
              <option value="02:00 PM">02:00 PM</option>
              <option value="02:30 PM">02:30 PM</option>
              <option value="03:00 PM">03:00 PM</option>
              <option value="03:30 PM">03:30 PM</option>
              <option value="04:00 PM">04:00 PM</option>
              <option value="04:30 PM">04:30 PM</option>
              <option value="05:00 PM">05:00 PM</option>
              <option value="05:30 PM">05:30 PM</option>
              <option value="06:00 PM">06:00 PM</option>
              <option value="06:30 PM">06:30 PM</option>
              <option value="07:00 PM">07:00 PM</option>
              <option value="07:30 PM">07:30 PM</option>
              <option value="08:00 PM">08:00 PM</option>
              <option value="08:30 PM">08:30 PM</option>
              <option value="09:00 PM">09:00 PM</option>
              <option value="09:30 PM">09:30 PM</option>
              <option value="10:00 PM">10:00 PM</option>
              <option value="10:30 PM">10:30 PM</option>
              <option value="11:00 PM">11:00 PM</option>
              <option value="11:30 PM">11:30 PM</option>
              <option value="12:00 AM">12:00 AM</option>
              <option value="12:30 AM">12:30 AM</option>
              <option value="01:00 AM">01:00 AM</option>
              <option value="01:30 AM">01:30 AM</option>
              <option value="02:00 AM">02:00 AM</option>
              <option value="02:30 AM">02:30 AM</option>
              <option value="03:00 AM">03:00 AM</option>
              <option value="03:30 AM">03:30 AM</option>
              <option value="04:00 AM">04:00 AM</option>
              <option value="04:30 AM">04:30 AM</option>
              <option value="05:00 AM">05:00 AM</option>
              <option value="05:30 AM">05:30 AM</option>
              <option value="06:00 AM">06:00 AM</option>
              <option value="06:30 AM">06:30 AM</option>
              <option value="07:00 AM">07:00 AM</option>
              <option value="07:30 AM">07:30 AM</option>
              <option value="08:00 AM">08:00 AM</option>
              <option value="08:30 AM">08:30 AM</option>
              <option value="09:00 AM">09:00 AM</option>
              <option value="09:30 AM">09:30 AM</option>
              <option value="10:00 AM">10:00 AM</option>
              <option value="10:30 AM">10:30 AM</option>
              <option value="11:00 AM">11:00 AM</option>
              <option value="11:30 AM">11:30 AM</option>
            </select>
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('length_of_stay')">
            <label class="longLabel">Length of Stay</label>
            <!-- <input type="date" required class="form-control textEntry" name="arrival_time" v-model="form.arrival_time" /> -->
            <select required class="form-control textEntry" v-model="form.length_of_stay">
              <option value="">Select Length</option>
              <option v-for="val in stay_options" :value="val">{{val.text}} (${{val.price}})</option>
            </select>
          </div>
          <div class="col-12 float-left mt-2">
            <b>Site Types</b><br />
            - Standard (Includes full hook-ups and paved pull-through site.)<br />
            - Deluxe (Includes all standard amenities plus patio, picnic table, and hilltop site. Add $4 per day.)<br />
            - Premium (Includes all standard amenities plus a large patio, large picnic table, and gazebo. Add $19 per day.)<br /><br />
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('site_type')">
            <label class="longLabel">Choose Site Type</label>
            <select required class="form-control textEntry" v-model="form.site_type">
              <option value="">Select Site Type</option>
              <option v-for="val in site_type_options" :value="val">{{val.text}} (+${{val.price}})</option>
            </select>
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('site_selected')">
            <label>Choose Parking Spot</label>
            <div v-if="sites_available">
              <select v-if="sites_available.length && !reservationLoading" required class="form-control textEntry" v-model="form.site_selected">
                <option value="">Select Parking Spot</option>
                <option v-for="val in sites_available" :value="val">#{{val.site_spot_number}}</option>
              </select>
              <div v-else-if="!sites_available.length && !reservationLoading" style="padding-top:3px;float:left;color:red;">
                <b>Sorry! There are no {{this.form.site_type.text}} parking spots available for the date and time you selected.</b>
              </div>
              <small-loader v-else-if="reservationLoading"></small-loader>
            </div>
            <div v-else-if="reservationLoading">
              <small-loader></small-loader>
            </div>
            <div v-else-if="!sites_available" style="padding-top:3px;color:#78ccfa;">
              Once you have filled in date, time, length of stay, and site type, parking site(s) will show...
            </div>
          </div>
          <hr style="width:90%;height:2px;background-color: #e0e0e0;margin-left: 5%;float:left;" />
          <div class="col-12 float-left formField longLabel">
            <label class="longLabel">Passport America Member?</label>
            <div style="text-align:center;">
              <a class="btn btn-sm" style="color:#000;" :class="form.passport_america_member === 'No' ? 'btn-info' : 'btn-outline-info'" @click="form.passport_america_member = 'No'">No</a>
              <a class="btn btn-sm" style="color:#000;" :class="form.passport_america_member === 'Yes' ? 'btn-info' : 'btn-outline-info'" @click="form.passport_america_member = 'Yes'">Yes</a>
            </div>
          </div>
          <div class="col-12 float-left formField longLabel" v-if="form.passport_america_member === 'Yes'" :class="configureEntry('membership_id')">
            <label class="longLabel">Membership ID</label>
            <input type="text" required class="form-control textEntry" name="membership_id" v-model="form.membership_id" />
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('vehicle_license')">
            <label class="longLabel">Vehicle License #</label>
            <input type="text" required class="form-control textEntry" name="vehicle_license" v-model="form.vehicle_license" />
          </div>
          <div class="col-12 float-left formField longLabel" :class="configureEntry('hear_about_us')">
            <label class="longLabel">How did you hear about us?</label>
            <select required class="form-control textEntry" v-model="form.hear_about_us">
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
        <div class="col-12 float-left text-center" v-if="configureShow()">
          <!-- <button class="btn btn-sm btn-success">Submit Reservation</button> -->
          By clicking the button to pay with paypal you agree to our <a class="link" href="https://rvselfpark.com/termsconditions" target="_blank">terms and conditions.</a>
          <PayPal
            v-if="!loading"
            :amount="calculateTotalPrice()"
            currency="USD"
            @payment-cancelled="paymentCancelled"
            @payment-completed="paymentCompleted"
            :client="credentials"
            >
          </PayPal>
          <div v-else class="background" style="color:#fff;">
            <h4>
              <small-loader></small-loader>
            </h4>
          </div>
        </div>
        <div class="col-12 float-left text-center" style="color:red;" v-else>
          PayPal button will appear when everything is filled in.
        </div>
      </form>

      <!-- <div class="col-12 float-left text-center">
            <button class="btn btn-sm btn-info" @click="refundPaypal()">Refund</button>
      </div> -->
    </card>
  </div>
</template>

<script>
import PayPal from 'vue-paypal-checkout-v'
import Swal from 'sweetalert2'
import VueTimepicker from 'vue2-timepicker'
import DatePicker from 'vue2-datepicker';
// var moment = require('moment')
// Vue.use(Swal)
var methods = {}

methods.submitForm = function (paypalId) {
  if (this.configureShow()) {
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
    var totalPrice = this.calculateTotalPrice()
    var tax = this.configureTaxPrice()
    var totalSitePrice = this.configureSitePrice()
    const postData = {
      arrival_hour: arrivalHour,
      arrival_minutes: this.arrival_minutes,
      ampm: this.ampm,
      date_in: this.form.date_in,
      stay_options: this.form.length_of_stay,
      site_id: 1,
      site_type: this.form.site_type,
      form: this.form,
      total_price: totalPrice,
      paypalId: paypalId,
      tax: tax,
      total_site_price: totalSitePrice
    }
    axios.post('/api/submitReservation', postData).then(response => {
      if (response.status === 201 || response.status === 200) {
        console.log(response)
        // Swal.fire('Success!', 'You have successfully reserved your spot. Please be sure to check your spam for your confirmation email.', 'success')
        Swal.fire({
          // position: 'top-end',
          icon: 'success',
          title: 'Success!',
          text: 'You are being redirected...',
          showConfirmButton: false,
          timer: 3000
        })
        this.loading = false
        this.clearForm()
        window.location.href = "https://rvselfpark.com/paymentconfirmation";
      }
    }).catch(error => {
        console.log('failed')
        const postData = {
          total_price: totalPrice,
          paypalId: paypalId,
          error: error
        }
        console.log(postData)
        axios.post('/api/refundPaypal', postData).then(response => {
          if (response.status === 201 || response.status === 200) {
            Swal.fire({
              icon: 'warning',
              title: 'Error!',
              text: 'It looks like an error occurred when trying to process your reservation. You have been refunded the amount you paid us. If this keeps happening, please give us a call at 573-259-6678.'
            })
            this.loading = false
            console.log(response)
          }
        }).catch(error => {
          console.log(error)
        })
    })
  }
}

methods.checkDisable = function () {
  return moment().format('YYYY-MM-DD')
}

methods.configureEntry = function (value) {
  if (this.form[value] !== '' && this.form[value]) {
    return 'filled'
  }
}

methods.refundPaypal = function () {
  const postData = {
    total_price: 44.08,
    paypalId: '9EE031093E6635626'
  }
  axios.post('/api/refundPaypal', postData).then(response => {
    console.log(response)
    if (response.status === 201 || response.status === 200) {
      console.log(response)
    }
  }).catch(error => {
    console.log(error)
  })
}

methods.clearForm = function () {
  this.form = {
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
        length_of_stay: '',
        passport_america_member: 'No',
        membership_id: '',
        site_type: '',
        vehicle_license: '',
        hear_about_us: '',
        site_selected: ''
      }
  this.arrival_hour = ''
  this.arrival_minutes = '00'
  this.ampm = 'AM'
}

methods.configureShow = function () {
  var arr = ['first_name', 'last_name', 'address', 'city', 'state', 'zip_code', 'phone', 'email', 'date_in', 'site_selected', 'vehicle_license', 'hear_about_us', 'site_type', 'arrival_time', 'length_of_stay']
  var ready = true
  var that = this
  arr.forEach(function (val) {
    if (that.form[val] === '' || !that.form[val]) {
      ready = false
    }
  })
  if (that.form.passport_america_member === 'Yes') {
    if (that.form.membership_id === '' || !that.form.membership_id) {
      ready = false
    }
  }
  console.log(ready)
  return ready
}

methods.paymentCancelled = function (e) {
  console.log('payment cancelled')
  console.log(e)
}

methods.paymentCompleted = function (e) {
  console.log(e)
  this.loading = true
  var id = e.transactions[0].related_resources[0].sale.id
  this.submitForm(id)
}

methods.configureSitePrice = function () {
  var calc = 0
  calc = this.form.length_of_stay.price + (this.form.site_type.price * this.form.length_of_stay.days)
  if (calc) {
    return parseFloat(calc).toFixed(2)
  } else {
    return '---'
  }
}

methods.calculateTotalPrice = function () {
  var calc = 0
  if (this.form.passport_america_member === 'Yes' && this.form.membership_id !== '') {
    calc = parseFloat(parseFloat(this.form.length_of_stay.price) + parseFloat(parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days)))
    if (parseInt(calc) < 39) {
      calc = parseFloat(19.50)
    } else {
      calc = parseFloat(calc - 19.50)
    }
    calc += 1.50
  } else {
    calc = parseFloat(parseFloat(this.form.length_of_stay.price) + parseFloat(parseFloat(this.form.site_type.price) * parseFloat(this.form.length_of_stay.days)))
    calc += 1.50
  }
  console.log(calc)
  var tax = parseFloat(parseFloat(calc) * .0885).toFixed(2)
  calc += parseFloat(tax)
  console.log(calc)
  if (calc) {
    calc = calc.toFixed(2)
    return calc
  } else {
    return '---'
  }
}

methods.configureTaxPrice = function () {
  var calc = 0
  if (this.form.passport_america_member === 'Yes' && this.form.membership_id !== '') {
    calc = this.form.length_of_stay.price + (this.form.site_type.price * this.form.length_of_stay.days)
    if (parseInt(calc) < 39) {
      calc = parseFloat(19.50)
    } else {
      calc = parseFloat(calc - 19.50)
    }
    calc += 1.50
  } else {
    calc = this.form.length_of_stay.price + (this.form.site_type.price * this.form.length_of_stay.days)
    calc += 1.50
  }
  if (calc) {
    var tax = parseFloat(parseFloat(calc) * .0885).toFixed(2)
    return tax
  } else {
    return '---'
  }
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
    site_type: this.form.site_type,
    arrival_time: this.form.arrival_time
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
  name: 'reservations_page',
  components: {
    PayPal,
    VueTimepicker,
    DatePicker
  },
  watch: {
    'form.date_in': function () {
      console.log(this.form.date_in)
      if (this.form.date_in) {
        if (this.form.arrival_time && this.form.site_type && this.form.length_of_stay) {
          this.checkReservations()
          this.form.site_selected = ''
        }
      } else {
        this.sites_available = null
        this.form.site_selected = ''
      }
    },
    arrival_hour: function () {
      if (this.form.date_in !== '') {
        this.checkReservations()
        this.form.site_selected = ''
      }
    },
    'arrival_time_temp': function () {
      // var hour = false
      // var min = false
      // var ampm = false
      // if (this.arrival_time_temp.substr(0,2) !== 'hh') {
      //   hour = true
      // }
      // if (this.arrival_time_temp.substr(3,2) !== 'mm') {
      //   min = true
      // }
      // if (this.arrival_time_temp.substr(6,2) === 'AM' || this.arrival_time_temp.substr(6,2) === 'PM') {
      //   ampm = true
      // }
      if (this.arrival_time_temp !== '') {
        var d = moment(this.arrival_time_temp, "hh:mm A").format('HH:mm:ss')
        this.form.arrival_time = d
        console.log(this.form.arrival_time)
        if (this.form.date_in && this.form.site_type) {
          this.checkReservations()
          this.form.site_selected = ''
        }
      } else {
        this.sites_available = null
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
      if (this.form.length_of_stay) {
        if (this.form.arrival_time && this.form.date_in && this.form.site_type) {
          this.checkReservations()
          this.form.site_selected = ''
        }
      } else {
        this.sites_available = null
        this.form.site_selected = ''
      }
    },
    'form.site_type': function () {
      if (this.form.site_type) {
        if (this.form.arrival_time && this.form.date_in && this.form.length_of_stay) {
          this.checkReservations()
          this.form.site_selected = ''
        }
      } else {
        this.sites_available = null
        this.form.site_selected = ''
      }
    }
  },
  data () {
    return {
      credentials: {
        sandbox: 'AUMqjcGQkvMNp4Q2LgbmoLuIKPq0IhfPZ8YvpxRr0okZJLAmtBheTeM2PoalZ4C3nAbFcRPK7bX1OK-n',
        production: 'AfyR1FhkNejm2j2bvtEYCHLANZTM8Ncig-oT_1b8ls2TFisy0wh8w5UPOU5XUouc8RfMbVZYY8IGekVh'
      },
      disableDate: '',
      loading: false,
      arrival_time_temp: '',
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
        length_of_stay: '',
        passport_america_member: 'No',
        membership_id: '',
        site_type: '',
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
        'Referral',
        'Returning Guest'
      ],
      arrival_hour: '',
      arrival_minutes: '00',
      ampm: 'PM',
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
    console.log('mounted')
    this.disableDate = moment().format('YYYY-MM-DD')
    console.log(this.disableDate)
    // axios.post('http://rv_server.test/api/getReservations').then(response => {
    //   console.log(response)
    // })
  }
}
</script>

<style lang="scss" scoped>
@import '~vue2-timepicker/dist/VueTimepicker.css';
@import '~vue2-datepicker/index.css';

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

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.filled {
  input {
    border: 1px solid green !important;
  }
  .mx-datepicker {
    border: 1px solid green !important;
  }
  span {
    border: 1px solid green !important;
    .display-time {
      border: 1px solid green !important;
    }
    input {
      border: 1px solid green !important;
    }
  }
  select {
    border: 1px solid green !important;
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
      width: calc(100% - 250px) !important;
      float:left;

      select {
        width:100% !important;
      }
    }
    @media (max-width: 600px) {

      .textEntry {
        width: 100% !important;float:left;
      }
      div {
        width:100% !important;float:left;
      }
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
