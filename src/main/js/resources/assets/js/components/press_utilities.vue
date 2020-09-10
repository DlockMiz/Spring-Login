<template>
    <div class="col-12 float-left mb-2">
        <card title="Parking Spot Utilities" :have_carot="true" from="parking_spot" class="mt-2">
            <iframe src="http://23.152.32.242:1880/ui/#!/0?socketid=RAA1gkkEEfXmdBmDAABR" style="width:100%;height:800px;float:left;"></iframe>
        </card>
    </div>
</template>
<script>
var methods = {}

export default {
    name: "ParkingSpotUtilities",
    data: function () {
    	return {

    	}
    },
    methods: methods,
    mounted: function() {

    },
    destroyed: function() {

    }
}
</script>
