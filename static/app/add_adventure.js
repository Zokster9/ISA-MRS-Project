Vue.use(vuelidate.default)

Vue.component("add-adventure", {
    template:`
    <form class="w-50 mx-auto mt-5">
        <h2> New adventure </h2>
        <div class="form-group mb-3">
            <label for="adventureName">Adventure name:</label>
            <input v-model="form.adventureName" type="text" class="form-control" placeholder="This field is required!">
        </div>
        <div class="form-group mb-3">
            <label for="descriptionNameId">Description: </label>
            <textarea v-model="form.description" class="form-control" id="descriptionNameId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="rulesOfConduct">Rules of conduct: </label>
            <textarea v-model="form.rulesOfConduct" class="form-control"  id="rulesOfConductId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="price">Price: </label>
            <input v-model="form.price" class="form-control" id="priceId" placeholder="This field is required!">
            <small id="priceHelp" class="form-text text-muted"> Price has to be at least 5 dollars.</small>
        </div>
        <div class="form-group mb-3">
            <label for="maxNumOfPeople">Maximum number of people: </label>
            <input v-model="form.maxNumOfPeople" class="form-control" id="maxNumOfPeopleId" placeholder="This field is required!">
            <small id="maxHelp" class="form-text text-muted"> Maximum number of people should at least be 1.</small>
        </div>
        <div class="form-group mb-3">
            <label for="fishingEquipment">Fishing equipment: </label>
            <textarea v-model="form.fishingEquipment" class="form-control" id="fishingEquipmentId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="reservationCancellationConditions">Reservation cancellation conditions: </label>
            <textarea v-model="form.reservationCancellationConditions" class="form-control" id="fishingEquipmentId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="instructorBiography">Instructor biography: </label>
            <textarea v-model="form.instructorBiography" class="form-control" id="instructorBiographyId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="pictures">Pictures: </label>
            <input v-model="form.pictures" accept="image/*" @change="uploadImage($event)" class="form-control" id="picturesId" placeholder="This field is required!">
        </div>
        <br>
        <button @click="sendData" type="submit" @ :disabled="$v.form.$invalid" class="btn btn-primary float-end" >Add adventure</button> <!-- videti kako ovo -->
    </form>
    `,
    data(){
        return {
            form:{
                adventureName: "",
                description: "",
                rulesOfConduct: "",
                price: 5,
                maxNumOfPeople: 1,
                fishingEquipment: "",
                reservationCancellationConditions: "",
                instructorBiography: "",
                pictures: null // ovo menjati vrv
            }
        }
    },
    methods: {
        sendData(){
            axios.post("neka_Putanja",{
                adventureName: this.form.adventureName,
                description: this.form.description,
                rulesOfConduct: this.form.rulesOfConduct,
                price: this.form.price,
                maxNumOfPeople: this.form.maxNumOfPeople,
                fishingEquipment: this.form.fishingEquipment,
                reservationCancellationConditions: this.form.reservationCancellationConditions,
                instructorBiography: this.form.instructorBiography,
                pictures: this.form.pictures
                //zatim header jwt token
                //zatim then(response => { router.puhs("vrv stranica instruktora")
            })
        }
    },
    validations:{
        form:{
            adventureName: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            description: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            rulesOfConduct: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            price: {
                required: validators.required,
                minValueValue: validators.minValue(5)
            },
            maxNumOfPeople: {
                required: validators.required,
                minValueValue: validators.minValue(1)
            },
            fishingEquipment: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            reservationCancellationConditions: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            instructorBiography: {
                required: validators.required,
                minLength: validators.minLength(5)
            }
        }
    }

})