Vue.use(vuelidate.default)

Vue.component("registration-requests", {
    template:`
        <div class="row justify-content-center">
         <div class="col-auto">
            <table class="table table-striped table-responsive table-bordered">
                <thead>
                    <th colspan="4" class="text-center"> Registration Requests </th>
                </thead>
                <tbody>
                    <tr v-for="user in unactivatedUsers" :key="user.id">
                        <td> {{ user.name }} {{ user.surname }} </td>
                        <td> {{ user.registrationReason }} </td>
                        <td> <button type="button" class="btn btn-success" @click="acceptUser(user.id)">Accept registration</button> </td>
                        <td> <button type="button" class="btn btn-danger"> Decline registration</button> </td>
                    </tr>
                </tbody>
            </table>
         </div>
        </div>
    `,
    data(){
        return {
            unactivatedUsers: [],
        }
    },
    methods: {
      acceptUser(id){
          axios.post("users/accept/" + id).then((response) => {
              axios.get("/users/inactive").then((response) => {
                  this.unactivatedUsers = response.data;
              })
          })
      }
    },
    mounted(){
        axios.get("/users/inactive").then((response) => {
            this.unactivatedUsers = response.data;
        })
    }
})