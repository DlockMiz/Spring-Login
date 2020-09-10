<template>
  <nav class="navbar navbar-expand-lg navbar-expand-md navbar-expand-sm navbar-light bg-white">
    <div v-if="user" class="container">
      <!-- <div style="position:absolute;right:10px;width:50px;height:50px;">
        Hi
      </div> -->
      <div class="d-block">
        <router-link style="float:left;" to="/">
          <!-- <img style="height:40px;margin-top:-4px;" src="./../../images/mpo_white_600_200.png" /> -->
        </router-link>
        <i @click="hamburgerClicked()" style="font-size:20px;margin-top:5px;position:absolute;left:15px;" class="d-block d-sm-none fa fa-list float-left"></i>
      </div>
      <div id="navbarToggler" style="float:left;" class="collapse navbar-collapse navbarTest">
        <ul class="navbar-nav ml-auto">
          <template>

            <li class="nav-item">
              <router-link :to="{ name: 'dashboard' }" class="dropdown-item">
                Dashboard
              </router-link>
            </li>

            <!-- <li class="nav-item">
              <router-link :to="{ name: 'reservations_table' }" class="dropdown-item">
                Reservations Table
              </router-link>
            </li> -->

            <li class="nav-item">
              <router-link :to="{ name: 'new_reservation' }" class="dropdown-item">
                Reservations Page
              </router-link>
            </li>

            <li class="nav-item">
              <router-link :to="{ name: 'add_reservation' }" class="dropdown-item">
                Add Existing Reservation
              </router-link>
            </li>

            <li class="nav-item">
              <a href="#" class="dropdown-item" @click.prevent="logout">
                <i class="fa fa-sign-out-alt"></i>
                {{ $t('logout') }}
              </a>
            </li>
            
            <!-- <li v-if="user" class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-dark"
                 href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img :src="user.photo_url" class="rounded-circle profile-photo mr-1">
                <label id="userName">{{ user.name.split(' ')[0] }}</label>
              </a>
              <div class="dropdown-menu" style="z-index:2000;position:absolute;width:100%;">
                <router-link :to="{ name: 'settings.profile' }" class="dropdown-item">
                  <fa icon="user" fixed-width/>
                  My Profile
                </router-link>

                <div class="dropdown-divider"/>
                <a href="#" class="dropdown-item" @click.prevent="logout">
                  <fa icon="sign-out-alt" fixed-width/>
                  {{ $t('logout') }}
                </a>
              </div>
            </li> -->
            <!-- Guest -->
            <!-- <li v-if="!user" class="nav-item">
              <router-link :to="{ name: 'login' }" class="dropdown-item">
                {{ $t('login') }}
              </router-link>
            </li>

            <li v-if="!user" class="nav-item">
              <router-link :to="{ name: 'register' }" class="dropdown-item">
                {{ $t('register') }}
              </router-link>
            </li> -->
          </template>
          
            <!-- <li class="nav-item">
              <router-link :to="{ name: 'register' }" class="dropdown-item pl-3" active-class="active">
                {{ $t('register') }}
              </router-link>
            </li> -->
        </ul>
      </div>

    </div>
  </nav>
</template>

<script>
import { mapGetters } from 'vuex'
import LocaleDropdown from './LocaleDropdown'

export default {
  components: {
    LocaleDropdown
  },

  data: () => ({
    appName: window.config.appName
  }),

  computed: mapGetters({
    user: 'auth/user',
    show_started: 'auth/show_started'
  }),

  mounted: function () {
  	// console.log(this.user)
    // this.$store.dispatch('auth/checkShowStart')
  },

  methods: {
    async logout () {
      // Log out the user.
      await this.$store.dispatch('auth/logout')

      // Redirect to login.
      this.$router.push({ name: 'login' })
    },
    hamburgerClicked () {
      // console.log('clickedddd')
      // if ($('.navbarTest').hasClass('collapse')) {
      //   console.log('collllll')
      //   $('.navbarTest').removeClass('collapse');
      // } else {
      //   console.log('elseeeee')
      //   $('.navbarTest').addClass('collapse');
      // }
      if ($('.collapse').hasClass('collapse')) {
        $('.collapse').removeClass('collapse');
      } else {
        $('.navbar-collapse').addClass('collapse');
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@media (max-width: 850px) {
  #userName {
    display: none;
  }
}

#overlay {
  position:fixed;
  top:0;left:0;right:0;bottom:0;
  width:100%;
  height:100%;
  background:rgba(0,0,0,.3);
  text-align:center;
  font-size:35px;
  z-index:100000;
}
.profile-photo {
  width: 1.5rem;
  height: 1.5rem;
  margin: -.375rem 0;
}
.container {
  width:100% !important;
  max-width:100% !important;
}
li {
  height: 40px;
  padding: 4px 8px !important;
  color:#fff;
}
a {
  height: 32px;
  line-height:32px;
  padding:0 4px;
  color:#fff;
}
.brand-image {
  height: 60px;
  position: absolute;
  left: 0;
  top: 0;
  padding: 6px;
  background-color: #fff !important;
  border-bottom-right-radius: 6px;
  z-index:10;
}
</style>
