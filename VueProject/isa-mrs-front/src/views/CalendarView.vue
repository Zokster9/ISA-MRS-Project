<template>
    <div>
        <NavbarUser></NavbarUser>
        <br><br><br>
        <div class="w-50 mx-auto mt-5">
            <div>
                <FullCalendar :options="calendarOptions" />
            </div>
        </div>
    </div>
</template>

<script>
    import '@fullcalendar/core/vdom'
    import FullCalendar from '@fullcalendar/vue'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import interactionPlugin from '@fullcalendar/interaction'
    import axios from 'axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

export default {
    components: {
        FullCalendar, NavbarUser
    },
    data() {
        return {
        calendarOptions: {
            plugins: [ dayGridPlugin, interactionPlugin ],
            initialView: 'dayGridMonth',
            dateClick: this.handleDateClick,
            events: [],
			displayEventTime: false
        }
        }
    },
    methods: {
        handleDateClick: function(arg) {
        alert('date click! ' + arg.dateStr)
        }
    },
    mounted() {
        if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner" || window.sessionStorage.getItem("role") === "ROLE_shipOwner" || window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
            axios.get('http://localhost:8088/services/get-service-availability/' + this.$route.params.id, {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                }
            })
            .then((response) => {
                for (let availability of response.data) {
                    this.calendarOptions.events.push({
                        title: "Period of service availability",
                        color: "green",
                        start: new Date(availability.dateFrom),
                        end: new Date(availability.dateTo)
                    });
                }
            });
            let path = "";
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") path = "retreats";
            else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") path = "ships";
            else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") path = "adventures";
            axios.get('http://localhost:8088/' + path + '/get-actions/' + this.$route.params.id, {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                }
            })
            .then((response) => {
                for (let action of response.data) {
                    this.calendarOptions.events.push({
                        title: "Action",
                        color: "blue",
                        start: new Date(action.dateFrom),
                        end: new Date(action.dateTo)
                    });
                }
            })
        }
        else {
            router.push("/");
        }
    }
}
</script>