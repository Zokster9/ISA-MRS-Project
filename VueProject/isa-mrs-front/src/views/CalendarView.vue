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
            events: [],
			displayEventTime: false,
            eventClick: this.eventClickHandler
        }
        }
    },
    methods: {
        eventClickHandler(click){
            let event = click.event;
            if (event.extendedProps.status === "FINISHED") {
                router.push('/write-report/' + event.id);
            }
        },
        getAvailability() {
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
        },
        getActions() {
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
            });
        },
        getReservations() {
            axios.get('http://localhost:8088/reservations/reservations-for-service/' + this.$route.params.id, {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                }
            }).then((response) => {
                for (let reservation of response.data) {
                    this.calendarOptions.events.push({
                        title: "Reservation",
                        color: "red",
                        id: reservation.id,
                        status: reservation.status,
                        start: new Date(reservation.fromDate),
                        end: new Date(reservation.toDate)
                    });
                }
            });
        }
    },
    mounted() {
        if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner" || window.sessionStorage.getItem("role") === "ROLE_shipOwner" || window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
            this.getAvailability();
            this.getActions();
            this.getReservations();
        }
        else {
            router.push("/");
        }
    }
}
</script>