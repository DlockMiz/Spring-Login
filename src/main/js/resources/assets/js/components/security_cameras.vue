<template>
    <div class="col-12 float-left mt-2">
        <card title="Security Cameras" :have_carot="true" from="security_cameras">
            <iframe src="https://dartstoc.com/rvsecure.aspx" style="width:100%;height:600px;float:left;"></iframe>
        </card>
    </div>
</template>
<script>
var methods = {}

export default {
    name: "SecurityCameras",
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
