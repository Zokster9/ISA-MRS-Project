<template>
    <div>
    <NavbarUser></NavbarUser>
    <form class="w-50 mx-auto mt-5">
        <h2> Adventure info </h2>
            <div class="form-group mb-3">
                <label>Name</label>
                <input v-model="form.name" type="text" class="form-control"/>
                <p v-if="!nameIsValid" class="alert alert-danger">Name is required.</p>
            </div>
            <div class="form-group mb-3">
                <label>Description</label>
                <textarea v-model="form.description" type="text" class="form-control"/>
                <p v-if="!descriptionIsValid" class="alert alert-danger">Description is required.</p>
            </div>
            <div class="form-group mb-3">
                <label>Country</label>
                <input v-model="form.country" readonly type="text" class="form-control"/>
                <p v-if="!countryIsValid" class="alert alert-danger">Country is required, and it starts with first capital letter.</p>
            </div>
            <div class="form-group mb-3">
                <label>City</label>
                <input v-model="form.city" readonly type="text" class="form-control"/>
                <p v-if="!cityIsValid" class="alert alert-danger">City is required, and it starts with first capital letter.</p>
            </div>
            <div class="form-group mb-3">
                <label>Address</label>
                <input v-model="form.address" readonly type="text" class="form-control"/>
                <p v-if="!addressIsValid" class="alert alert-danger">Address is required.</p>
            </div>
            <div class="form-group mb-3">
                <label>Price</label>
                <input v-model.number="form.price" type="number" class="form-control"/>
                <p v-if="!priceIsValid" class="alert alert-danger">Price is required, and it must be number greater than 0.</p>
            </div>
            <div class="form-group mb-3">
                <label> Maximum number of people </label>
                <input v-model.number="form.maxNumOfPeople" type="number" class="form-control"/>
                <p v-if="!maxNumOfPeopleIsValid" class="alert alert-danger">Maximum amount of people is required, and it must be number greater than 0.</p>
            </div>
            <div class="form-group mb-3">
                <label> Reservation cancellation conditions </label>
                <input v-model="form.reservationCancellationConditions" type="text" class="form-control"/>
                <p v-if="!reservationCancellationConditionsIsValid" class="alert alert-danger">Reservation cancellation conditions is required!</p>
            </div>
            <div class="form-group mb-3">
                <label> Instructor biography </label>
                <input v-model="form.instructorBiography" type="text" class="form-control"/>
                <p v-if="!instructorBiographyIsValid" class="alert alert-danger">Instructor biography is required!</p>
            </div>                                
            <div class="form-group mb-3">
                <label for="rulesOfConduct">Rules of conduct: </label>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.rulesOfConduct" value="Mobile phone must be turned off">
                    <label>Mobile phone must be turned off</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.rulesOfConduct" value="Must be properly dressed">
                    <label>Must be properly dressed</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.rulesOfConduct" value="Bring your own fishing equipment">
                    <label>Bring your own fishing equipment</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.rulesOfConduct" value="Can't be noisy">
                    <label>Can't be noisy</label>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="fishingEquipment">Fishing equipment: </label>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Hooks">
                    <label>Hooks</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Lines">
                    <label>Lines</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Floats">
                    <label>Floats</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Rods">
                    <label>Rods</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Reels">
                    <label>Reels</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Baits">
                    <label>Baits</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Lures">
                    <label>Lures</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Spears">
                    <label>Spears</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Nets">
                    <label>Nets</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Gaffs">
                    <label>Gaffs</label>
                </div>
                <div class="form-group mb-3">
                    <input type="checkbox" v-model="form.fishingEquipment" value="Traps">
                    <label>Traps</label>
                </div>
            </div>                
            <div class="form-group mb-3">
                <label>Additional services:</label>
                <br>
                <template v-for="tag in tags">
                    <div class="form-group mb-3" :key="tag.id">
                        <input type="checkbox" v-model="form.additionalServices" :value="tag.name" :key="tag.id"/>
                        <label :for="tag.key">{{tag.name}}</label>
                    </div>
                </template>
            </div>
            <div class="form-group mb-3">
                <label>Pictures:</label>
                <input accept="image/*" type="file" class="form-control" @change="addPicture($event)" multiple/>
            </div>
            <div class="form-group- mb-3">
                <button @click="changeAdventure" type="submit" class="btn btn-primary float-end">Update adventure</button>
            </div>
        </form>
    </div>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

    Vue.use(VueAxios, axios)
    export default {
        name: "UpdateAdventure",
        components: {
            NavbarUser
        },
        data() {
            return {
                form: {
                    name: null,
                    description: null,
                    country: null,
                    city: null,
                    address: null,
                    price: null,
                    rulesOfConduct: [],
                    maxNumOfPeople: null,
                    fishingEquipment: [],
                    reservationCancellationConditions: null,
                    instructorBiography: null,
                    additionalServices: [],
                    pictures: [],
                },
                adventure: null,
                tags: []
            }
        },
        computed: {
            nameIsValid() {
                return !!this.form.name;
            },
            descriptionIsValid() {
                return !!this.form.description;
            },
            countryIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.country) && !!this.form.country;
            },
            cityIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.city) && !!this.form.city;
            },
            addressIsValid() {
                return !!this.form.address;
            },
            instructorBiographyIsValid() {
                return !!this.form.instructorBiography;
            },
            reservationCancellationConditionsIsValid() {
                return !!this.form.reservationCancellationConditions;
            },
            maxNumOfPeopleIsValid() {
                return typeof this.form.maxNumOfPeople === 'number' && !!this.form.maxNumOfPeople && this.form.maxNumOfPeople > 0;
            },
            priceIsValid() {
                return typeof this.form.price === 'number' && !!this.form.price && this.form.price > 0;
            },
            formIsValid() {
                return this.nameIsValid && this.descriptionIsValid && this.countryIsValid && this.cityIsValid && this.addressIsValid && this.instructorBiographyIsValid && this.reservationCancellationConditionsIsValid && this.priceIsValid && this.maxNumOfPeopleIsValid;
            }
        },
        methods: {
            changeAdventure() {
                if (this.formIsValid) {
                    this.sendData();
                }
                else{
                    alert("alo");
                }
            },
            sendData() {
                axios.put("http://localhost:8088/adventures/updateAdventure/" + this.$route.params.id, {
                    name: this.form.name,
                    description: this.form.description,
                    country: this.form.country,
                    city: this.form.city,
                    street: this.form.address,
                    rulesOfConduct: this.form.rulesOfConduct,
                    pictures: this.form.pictures,
                    maxNumOfPeople: this.form.maxNumOfPeople,
                    price: this.form.price,
                    fishingEquipment: this.form.fishingEquipment,
                    reservationCancellationConditions: this.form.reservationCancellationConditions,
                    instructorBiography: this.form.instructorBiography,
                    additionalServices: this.form.additionalServices
                },
				{
					headers: {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("accessToken")
					}
				}).then(() => {
                    alert("Adventure updated!");
                    router.back();
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for this retreat exists.");
                    else alert("Error occured. Retreat has not been updated.");
                });
            },
            addPicture(e) {
                let files = e.target.files || e.dataTransfer.files;
                if (!files.length)
                    return;
                for (let file of files) this.form.pictures.push(file.name);
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                axios.get("http://localhost:8088/adventures/get/" + this.$route.params.id, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {
                    this.adventure = response.data;
                    this.form.name = response.data.name;
                    this.form.description = response.data.description;
                    this.form.country = response.data.country;
                    this.form.city = response.data.city;
                    this.form.address = response.data.street;
                    this.form.price = response.data.price;
                    this.form.rulesOfConduct = response.data.rulesOfConduct;
                    this.form.additionalServices = response.data.additionalServices;
                    this.form.pictures = response.data.pictures;
                    this.form.maxNumOfPeople = response.data.maxNumOfPeople;
                    this.form.fishingEquipment = response.data.fishingEquipment;
                    this.form.reservationCancellationConditions = response.data.reservationCancellationConditions;
                    this.form.instructorBiography = response.data.instructorBiography;
                    this.form.additionalServices = response.data.additionalServices;
                });
                axios.get("http://localhost:8088/tags/adventure", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {this.tags = response.data});
            }
            else {
                router.push("/");
            }
        }
    }
</script>