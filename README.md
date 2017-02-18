# STEAMworks
The Programming project for Team 3335's 2017 robot.
## Updating software
### Getting the National Instruments Update Suite
Go to Update Suite in order to image roboRio

[Download update suite](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599670-installing-the-frc-2017-update-suite-all-languages)

### Imaging the RoboRio
ALL robots reguire an up to date roboRio

After the update suite installation, connect a USB into the roborio USB port and connect it to the computer\(do not use an ethernet cable)

To open the imaging tool which is downloaded with NI's update suite go to **C:\Program Files (x86)\National Instruments\LabVIEW 2016\project\roboRIO Tool** and then click on **roboRIO_ImagingTool.exe**

After launching, the roboRIO Imaging Tool will scan for available roboRIOs and indicate any found in the top left box. The bottom left box will show the available image versions that may be loaded onto the roboRIO. The right hand column contains information and settings for the roboRIO selected in the top left pane.

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/309/346/original/508d4fe9-2e1c-4ac2-a852-7fdeaeba25dd.png?1484677916)

Choose to `Format Target` and select the latest image, then click reformat.

Imaging is complete if you see this:

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/309/341/original/c77ae106-441d-4811-b518-c72825ae5597.png?1484677907)

#### Downloading Silverlight
Before these next steps, we will need to get Microsoft Silverlight to run something called the Webdashboard.

Go to the silverlight home page

![Imgur](http://i.imgur.com/OJji8JBm.png)

[link to silverlight website](https://www.microsoft.com/silverlight/)

download silverlight

enjoy silverlight :smile:

### Updating RoboRio firmware

like the imaging process connect the computer to the RoboRio via usb/(do not use an ethernet)

also ensure you have downloaded the updateSuite

to get to the web dashboard you must first download silverlight/(refer to instructons below)

open internet explorer and in the search window enter 172.22.11.2 and click enter

Click Login. Enter "admin" in the Username field and leave the Password field blank. Then click update firmware.

![](http://s3.amazonaws.com/screensteps_live/images/Wpilib/273817/3/rendered/3fba91f1-4c49-4719-9f69-84610f1a0a5f_display.png?AWSAccessKeyId=AKIAJRW37ULKKSXWY73Q&Expires=1487440730&Signature=kjFn4hoQi3Jr1ckRhHbX69Q0I%2BQ%3D)

go to **c:\Program Files (x86)\National Instruments\Shared\Firmware\cRIO\76F2\** select the latest firmware (preferably 3.0.0f0) and click open

After confirming that the firmware is correct click `Update Firmware`

Do not close the browser or power off the robot until the message, "The firmware update completed successfully," appears.



### Updating PDP and PCM firmware
Using the webdashboard, go to to its home page, and you will find PCM and PDP parts:

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/312/190/medium/5962edf2-38cc-407e-9759-05040fb7ac57.png?1484777674)

Click on `Update Firmware`, which will load your file browser, and navigate to Documents\\FRC\\. There will be CRF files for both the PDP and PCM, respectively. Update them with this firmware.


