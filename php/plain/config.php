<?php

function getConfig()
{
    return array(

        // =========================================================================================
        // Application Settings
        // =========================================================================================

        // Trust in Lacuna Test PKI (for development purposes only!)
        "trustLacunaTestRoot" => true,
        // THIS SHOULD NEVER BE USED ON A PRODUCTION ENVIRONMENT!

        // -----------------------------------------------------------------------------------------
        // REST PKI
        // -----------------------------------------------------------------------------------------
        'restPki' => [

            // ====================================================
            //     >>>> PASTE YOUR REST PKI ACCESS TOKEN BELOW <<<<
            // ====================================================
            'accessToken' => 'qCzaYvkfrNEuSPSZ08wDidgmg5MBGCjwU2-A0D8l0DOUaWOqR_OVfZhbSNJ44dthx8uF3Wekj-MlHiMaBabziwK87JpXJXwFAlY8v_ccAkBzVq21d_UBPD4ZZH7P71j-rsPfBSilLk6fpUpMWGB-OwUlnW6j6OIR3FX90VXW1WgN2OI8HfnFZjLF2U-nvJyO5yt4dab2cS9B9m821u5JFGLLvlukDU_xg1wquLkxFEJEBppw_ESQNuWSJnILe1UYcdMdHVF9D-322cLkr7BoV0ZqZ2PsfEkqm9IGAjmfZSddPVRocIdQ9c4lB4d-PThF9QYoOdMoFZXwHHgjrlIjazQ_KfsEd2VaUuuqQPnVpnGBKnafqQiNC9C6FypEpAcn643RV-pcO7iDMi8XjCRelDlCMxrS08Rh4arf4Y-19SUfIGqcJL66iH7Phzs5ME2tFly1yG8UNJ6bxjzNfPOTIRXyy9058KpHUPs39W3ydt5I3Fme2ptwtczYmqzbh-nwPZSWig',
            // This is a TRIAL token. It will be expired at 30/08/2019.
            // If the REST PKI sample doesn't work, please contact our support by email: suporte@lacunasoftware.com

            // Address of your Rest PKI installation (with the trailing '/' character)
            "endpoint" => null,
        ],

        // -----------------------------------------------------------------------------------------
        // Amplia
        // -----------------------------------------------------------------------------------------
        'amplia' => [

            // The CA's id that will be used to issue a certificate using Amplia. We have configured
            // to the sample CA from sample subscription for these samples.
            'caId' => 'eaffa754-1fb5-474a-b9ef-efe43101e89f',

            // ======================================================
            //       >>>> PASTE YOUR AMPLIA API KEY BELOW <<<<
            // ======================================================
            'apiKey' => 'pki-suite-samples-01|7f8769803c7edc4988483db470b2336d31546239351cfb2576849a4d8f04aae8',
            // This is a TRIAL API key to use Amplia. It will expire at 30/08/2019.
            // If the Amplia's samples do not work please contact our support by email:
            // suporte@lacunasoftware.com

            // In order to use this sample on a "on premises" installation of Amplia, fill the
            // field below with the URL address of your REST PKI installation (with the trailing '/'
            // character).
            'endpoint' => null,
        ],

        // -----------------------------------------------------------------------------------------
        // PKI Express
        // ----------------------------------------------------------------------------------------

        "pkiExpress" => array(

            // List of custom trusted roots. In this sample, we will get the certificate files on
            // resources folder.
            "trustedRoots" => [],

            // Offline mode. Set this, if you want to PKI Express to run on offline mode. This mode
            // is useful when there is no network available.
            "offline" => false
        ),

        // -----------------------------------------------------------------------------------------
        // Web PKI
        // -----------------------------------------------------------------------------------------

        "webPki" => array(

            // Base64-encoded binary license for the Web PKI. This value is passed to Web PKI
            // component's constructor.
            "license" => null
        )

    );
}