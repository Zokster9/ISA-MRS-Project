Vue.use(vuelidate)
Vue.component("profile-page-fishing-instructor", {
    template:`
        <div class="d-flex flex-row">
            <div>
                <nav-fishing-instructor @selected-item="showSelectedOption"></nav-fishing-instructor>
            </div>
            <div class="mx-auto">
                <personal-info-fishing-instructor v-if="personalInfo === true" :user="user"></personal-info-fishing-instructor>
            </div>
        </div>
    `,
    data(){
        return{
            personalInfo: true,
            myServices: false,
            reservationHistory: false,
            myAvailability: false,
            actionsFastReservation: false,
            newReservation: false,
            writeReport: false,
            businessReports: false,
            myCalendar: false,
            user: "",
        }
    },
    methods:{
        showSelectedOption(selectedItem){
            if (selectedItem.personalInfo){
                this.personalInfo = true;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.myServices){
                this.personalInfo = false;
                this.myServices = true;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.reservationHistory){
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = true;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.myAvailability){
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = true;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.actionsFastReservation){
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = true;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.newReservation) {
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = true;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.writeReport) {
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = true;
                this.businessReports = false;
                this.myCalendar = false;
            } else if (selectedItem.businessReports) {
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = true;
                this.myCalendar = false;
            } else if (selectedItem.myCalendar) {
                this.personalInfo = false;
                this.myServices = false;
                this.reservationHistory = false;
                this.myAvailability = false;
                this.actionsFastReservation = false;
                this.newReservation = false;
                this.writeReport = false;
                this.businessReports = false;
                this.myCalendar = true;
            }
        }
    },
    //TODO:tu ce biti neki jwt za email
    mounted(){
        axios.get("users/findByEmail/lordje@gmail.com").then((response) =>{
            this.user = response.data
        })
    }
})