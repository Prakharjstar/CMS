console.log("Script loaded");
let currentTheme= getTheme();
changeTheme(currentTheme);

// for theme change button


function changeTheme(currentTheme){

    //set to web page

    document.querySelector('html').classList.add(currentTheme);

    //set the listner to change theme button
  

   const changeThemeButton=document.querySelector("#theme_change_button");
   changeThemeButton.querySelector("span").textContent= currentTheme =="light"? "Dark":"Light";
    changeThemeButton.addEventListener("click" ,() =>{
        
        const oldTheme=currentTheme;

        console.log("change theme button clicked");
        if(currentTheme=="dark"){
            currentTheme="light";
        }
        else{
            currentTheme="dark";
        }

        //local storage mai update karenge

        setTheme(currentTheme);
        //remove the current theme
        document.querySelector("html").classList.remove(oldTheme);

        //set the current theme
        document.querySelector("html").classList.add(currentTheme);

        changeThemeButton.querySelector('span').textContent=currentTheme=="light"?"Dark":"Light";

    });

}


//set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme",theme);
}

// get theme for local Storage
function getTheme(){
    let theme=localStorage.getItem("theme");
    return theme?theme:"light";
}