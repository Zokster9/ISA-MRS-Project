Vue.use(vuelidate.default)

Vue.component("account-termination-requests", {
    template:`
        <div class="row justify-content-center">
         <div class="col-auto">
         <br>
         <br>
            <table class="table table-striped table-responsive table-bordered mb-5"">
                <thead>
                    <th colspan="100%" class="text-center h2"> Account Termination Requests </th>
                </thead>
                <tbody>
                    <termination-requests-row v-for="terminationRequest in terminationRequests" :termination="terminationRequest"></termination-requests-row>
                </tbody>
            </table>
         </div>
        </div>
    `,
    data(){
        return {
            terminationRequests: [],
        }
    },
    mounted(){
        axios.get("/termination/findToTerminate").then((response) => {
            this.terminationRequests = response.data;
        })
    },
})