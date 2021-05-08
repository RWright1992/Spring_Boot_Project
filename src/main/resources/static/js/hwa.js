"use strict";

// Host:Port
const ADDR = "http://localhost:8080";

// Divs
const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");

// Buttons
const CREATE_BTN = document.querySelector("#create_btn");

// Inputs
const NAME = document.querySelector(".name-input");
const ARTIST = document.querySelector(".artist-input");
const YEAR = document.querySelector(".year-input");
const TYPE = document.querySelector(".type-input");

// Datepicker year only
$("#datepicker").datepicker( {
    format: "yyyy",
    viewMode: "years", 
    minViewMode: "years"
});

// Get request
const getAll = () => {
    axios.get(`${ADDR}/getAll`)
        .then((resp) => {
            RESULTS_DIV.innerHTML = "";
            const RESULTS = resp.data;
            for (let result of RESULTS) {
                printResult(result);
            }
        }).catch((err) => console.error(err))
}

// Post request
const create = () => {
    // Validate the form
    if (!validateForm()) {
        return;
    }

    // Input values
    const NAME_VALUE = NAME.value;
    const ARTIST_VALUE = ARTIST.value;
    const YEAR_VALUE = YEAR.value;
    const TYPE_VALUE = TYPE.value;

    // For checking input vals
    // console.log(`${NAME_VALUE}${ARTIST_VALUE}${YEAR_VALUE}${TYPE_VALUE}`);

    // New object to be created
    let obj = {
        "name": NAME_VALUE,
        "artist": ARTIST_VALUE,
        "year": YEAR_VALUE,
        "type": TYPE_VALUE
    };

    axios.post(`${ADDR}/create`, obj)
        .then((resp) => {
            statusMsg(true);
            getAll();
        }).catch((err) => {
            console.error(err);
            statusMsg(false);
        })
}

const statusMsg = (bool) => {
    CREATE_BTN.setAttribute("disabled", "true");
    if (bool) {
        const ALERT_SUC = document.createElement("div");
        ALERT_SUC.setAttribute("class", "alert alert-success");
        ALERT_SUC.innerHTML = "Creation Successful"
        FORM_DIV.appendChild(ALERT_SUC);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-success"));
            CREATE_BTN.removeAttribute("disabled");
        }, 3000)
    } else {
        const ALERT_ERR = document.createElement("div");
        ALERT_ERR.setAttribute("class", "alert alert-danger");
        ALERT_ERR.innerHTML = "Creation Failed";
        FORM_DIV.appendChild(ALERT_ERR);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-danger"));
            CREATE_BTN.removeAttribute("disabled");
        }, 3000);
    }
}

const printResult = (result) => {
    const P = document.createElement("p");
    const TEXT = document.createTextNode(`Name: ${result.name} Artist: ${result.artist} Year: ${result.year} Type: ${result.type}`);
    P.appendChild(TEXT);
    RESULTS_DIV.appendChild(P);
}

function validateForm() {
    const f = document.forms["createForm"];
    if (f["name"].value == "" || f["artist"].value == "" || f["year"].value == "" || f["type"].value == "") {
        alert("All fields require a value!");
        return false;
    }
    return true;
}