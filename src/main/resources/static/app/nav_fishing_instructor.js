Vue.component("nav-fishing-instructor", {
    template:`
        <nav class="nav nav-tabs flex-column">
            <button @click="setPersonalInfoTrue" class="nav-link text-start">Personal info</button>
            <button @click="setMyServicesTrue" class="nav-link text-start">My services</button>
            <button @click="setReservationHistoryTrue" class="nav-link text-start">Reservation history</button>
            <button @click="setMyAvailabilityTrue" class="nav-link text-start">My availability</button>
            <button @click="setActionsTrue" class="nav-link text-start">Actions for fast reservation</button>
            <button @click="setNewReservationTrue" class="nav-link text-start">New reservation</button>
            <button @click="setWriteReportTrue" class="nav-link text-start">Write a report</button>
            <button @click="setBusinessReportsTrue" class="nav-link text-start">Business reports</button>
            <button @click="setMyCalendarTrue" class="nav-link text-start">My calendar</button>
        </nav>
    `,
    data(){
        return {
            selectedItem: {
                personalInfo: true,
                myServices: false,
                reservationHistory: false,
                myAvailability: false,
                actionsFastReservation: false,
                newReservation: false,
                writeReport: false,
                businessReports: false,
                myCalendar: false
            }
        }
    },
    methods:{
        setPersonalInfoTrue(){
            this.selectedItem.personalInfo = true
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setMyServicesTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = true
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setReservationHistoryTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = true
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setMyAvailabilityTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = true
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setActionsTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = true
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setNewReservationTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = true
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setWriteReportTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = true
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setBusinessReportsTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = true
            this.selectedItem.myCalendar = false
            this.$emit('selected-item', this.selectedItem)
        },
        setMyCalendarTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.myServices = false
            this.selectedItem.reservationHistory = false
            this.selectedItem.myAvailability = false
            this.selectedItem.actionsFastReservation = false
            this.selectedItem.newReservation = false
            this.selectedItem.writeReport = false
            this.selectedItem.businessReports = false
            this.selectedItem.myCalendar = true
            this.$emit('selected-item', this.selectedItem)
        }
    }
})