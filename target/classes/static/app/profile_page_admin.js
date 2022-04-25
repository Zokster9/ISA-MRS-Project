Vue.use(vuelidate)
Vue.component("profile-page-admin", {
    template: `
        <div class="d-flex flex-row">
            <div>
                <nav-admin @selected-item="showSelectedOption"></nav-admin>
            </div>
            <div class="mx-auto">
                <registration-requests v-if="registrationRequests === true"></registration-requests>
                <account-termination-requests v-if="accountTerminationRequests === true"></account-termination-requests>
            </div>
        </div>
    `,
    data() {
        return {
            personalInfo: true,
            registrationRequests: false,
            accountTerminationRequests: false,
            addAdministrators: false,
            systemEntities: false,
            incomeInfo: false,
            businessReports: false,
            user: "",
        }
    },
    methods:{
        showSelectedOption(selectedItem) {
            if (selectedItem.personalInfo){
                this.personalInfo = true;
                this.registrationRequests = false;
                this.accountTerminationRequests = false;
                this.addAdministrators = false;
                this.systemEntities = false;
                this.incomeInfo = false;
                this.businessReports = false;
            } else if (selectedItem.registrationRequests){
                this.personalInfo = false;
                this.registrationRequests = true;
                this.accountTerminationRequests = false;
                this.addAdministrators = false;
                this.systemEntities = false;
                this.incomeInfo = false;
                this.businessReports = false;
            } else if (selectedItem.accountTerminationRequests){
                this.personalInfo = false;
                this.registrationRequests = false;
                this.accountTerminationRequests = true;
                this.addAdministrators = false;
                this.systemEntities = false;
                this.incomeInfo = false;
                this.businessReports = false;
            } else if (selectedItem.addAdministrators){
                this.personalInfo = false;
                this.registrationRequests = false;
                this.accountTerminationRequests = false;
                this.addAdministrators = true;
                this.systemEntities = false;
                this.incomeInfo = false;
                this.businessReports = false;
            } else if (selectedItem.systemEntities){
                this.personalInfo = false;
                this.registrationRequests = false;
                this.accountTerminationRequests = false;
                this.addAdministrators = false;
                this.systemEntities = true;
                this.incomeInfo = false;
                this.businessReports = false;
            } else if (selectedItem.incomeInfo){
                this.personalInfo = false;
                this.registrationRequests = false;
                this.accountTerminationRequests = false;
                this.addAdministrators = false;
                this.systemEntities = false;
                this.incomeInfo = true;
                this.businessReports = false;
            } else if (selectedItem.businessReports){
                this.personalInfo = false;
                this.registrationRequests = false;
                this.accountTerminationRequests = false;
                this.addAdministrators = false;
                this.systemEntities = false;
                this.incomeInfo = false;
                this.businessReports = true;
            }
        }
    }
})