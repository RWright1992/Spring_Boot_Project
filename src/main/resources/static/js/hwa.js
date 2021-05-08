"use strict";

const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");
const CREATE_BTN = document.querySelector("#create_btn");

// Variables used for testing post
const NAME = document.querySelector(".name-input");
const JOB = document.querySelector(".artist-input");
// const ALERT_SUC = document.querySelector("#onSuccess");
// const ALERT_ERR = document.querySelector("#onError");

const getTest = () => {
    axios
        .get("https://reqres.in/api/users")
        .then((resp) => {
            printResultsTest(resp.data.data);
        })
        .catch((err) => {
            console.err(err);
        })

    console.log("hello");
}

// Function used for testing get
const printResultsTest = (results) => {
    // RESULTS_DIV.innerHTML = "";
    for (let result of results) {
        const P = document.createElement("p");
        const TEXT = document.createTextNode(`First name: ${result.first_name} Last name: ${result.last_name} Email: ${result.email}`);
        P.appendChild(TEXT);
        RESULTS_DIV.appendChild(P);
    }
}

// Function used for testing post
const postTest = () => {
    const NAME_VALUE = NAME.value;
    const JOB_VALUE = JOB.value;

    console.log(`${NAME_VALUE}${JOB_VALUE}`);

    let obj = {
        "name": NAME_VALUE,
        "job": JOB_VALUE
    };

    axios
        .post("https://reqres.in/api/users", obj)
        .then((resp) => {
            CREATE_BTN.setAttribute("disabled", "true");
            console.log(resp);
            const ALERT_SUC = document.createElement("div");
            ALERT_SUC.setAttribute("class", "alert alert-success");
            ALERT_SUC.innerHTML = "Creation Successful"
            FORM_DIV.appendChild(ALERT_SUC);
            setTimeout(() => {
                FORM_DIV.removeChild(document.querySelector(".alert-success"));
                CREATE_BTN.removeAttribute("disabled");
            }, 3000)
            getTest(); 
        })
        .catch((err) => {
            console.log(err);
            const ALERT_ERR = document.createElement("div");
            ALERT_ERR.setAttribute("class", "alert alert-danger");
            ALERT_ERR.innerHTML = "Creation Failed";
            FORM_DIV.appendChild(ALERT_ERR);
            setTimeout(() => {
                FORM_DIV.removeChild(document.querySelector(".alert-danger"));
            }, 3000); 
        })
}
