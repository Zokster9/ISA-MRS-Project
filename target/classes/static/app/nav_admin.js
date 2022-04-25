Vue.component("nav-admin", {
    template: `
        <nav class="nav nav-tabs flex-column">
            <button @click="setPersonalInfoTrue" class="nav-link text-start">Personal info</button>
            <button @click="setRegistrationRequestsTrue" class="nav-link text-start">Registration requests</button>
            <button @click="setAccountTerminationRequestsTrue" class="nav-link text-start">Account termination requests</button>
            <button @click="setAddAdministratorsTrue" class="nav-link text-start">Add administrators</button>
            <button @click="setSystemEntitiesTrue" class="nav-link text-start">System entities</button>
            <button @click="setIncomeInfoTrue" class="nav-link text-start">Income information</button>
            <button @click="setBusinessReportsTrue" class="nav-link text-start">Business reports</button>
        </nav>
    `,
    data() {
        return {
            selectedItem: {
                personalInfo: true,
                registrationRequests: false,
                accountTerminationRequests: false,
                addAdministrators: false,
                systemEntities: false,
                incomeInfo: false,
                businessReports: false,
            },
        }
    },
    methods: {
        setPersonalInfoTrue(){
            this.selectedItem.personalInfo = true
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setRegistrationRequestsTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = true
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setAccountTerminationRequestsTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = true
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setAddAdministratorsTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = true
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setSystemEntitiesTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = true
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setIncomeInfoTrue(){
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = true
            this.selectedItem.businessReports = false
            this.$emit('selected-item', this.selectedItem)
        },
        setBusinessReportsTrue() {
            this.selectedItem.personalInfo = false
            this.selectedItem.registrationRequests = false
            this.selectedItem.accountTerminationRequests = false
            this.selectedItem.addAdministrators = false
            this.selectedItem.systemEntities = false
            this.selectedItem.incomeInfo = false
            this.selectedItem.businessReports = true
            this.$emit('selected-item', this.selectedItem)
        }
    }
})