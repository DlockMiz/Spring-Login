<template>
    <div class="col-12 float-left text-center pt-3">
        <reservations-table></reservations-table>
        <!-- <press-utilities></press-utilities> -->
        <site-spots :site_id="1"></site-spots>
        <security-cameras></security-cameras>
    </div>
</template>
<script>
import SecurityCameras from './../components/security_cameras';
import ReservationsTable from './reservations_table';
import SiteSpots from './../components/site_spots';
import PressUtilities from './../components/press_utilities';

export default {
    // ===Component name
    name: "Dashboard",
    // ===Props passed to component
    props: {},
    middleware: 'auth',
    // ===Components used by this component
    components: {
        SecurityCameras,
        SiteSpots,
        ReservationsTable,
        PressUtilities
    },
    // ====component Data properties
    data(){
        return{

        }
    },
    // ===Code to be executed when Component is mounted
    mounted() {
        console.log('dash')
    },
    // ===Computed properties for the component
    computed: {},
    // ===Component methods
    methods: {

    }
}
</script>
<!-- styles -->
<!-- adding scoped attribute will apply the css to this component only -->
<style scoped></style>
