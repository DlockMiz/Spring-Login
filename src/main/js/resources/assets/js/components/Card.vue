<template>
  <div class="card">
    <div v-if="title" @click="configureClick()" class="card-header" :class="have_carot ? 'pointer' : ''">
      {{ title }}

      <div v-if="have_carot" class="carotWrapper">
        <i class="fa fa-arrow-down" v-if="!showBody"></i>
        <i class="fa fa-arrow-up" v-if="showBody"></i>
      </div>
    </div>

    <div class="card-body" v-if="showBody">
      <slot/>
    </div>
  </div>
</template>

<script>
var methods = {}

methods.configureClick = function () {
  if (this.have_carot) {
    this.showBody = !this.showBody
    if (this.from) {
      this.$emit('carot-clicked', this.showBody)
      window.localStorage.setItem(this.from, JSON.stringify(this.showBody))
    }
  }
}

export default {
  name: 'Card',

  data: function () {
    return {
      showBody: true
    }
  },
  methods: methods,

  mounted: function () {
    if (this.from) {
      if (window.localStorage.getItem(this.from)) {
        this.showBody = JSON.parse(window.localStorage.getItem(this.from))
      }
    }
  },

  props: {
    title: { type: String, default: null },
    have_carot: {type: Boolean, default: false},
    from: {type: String, default: null}
  }
}
</script>
<style lang="scss" scoped>
  .card {
    .card-header {
      .carotWrapper {
        position:absolute;
        top:10px;
        right:10px;
        font-size:1em;

      }
    }

    .pointer {
      cursor: pointer !important;
    }
  }
</style>
