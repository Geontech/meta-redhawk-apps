# Meta-REDHAWK-Apps

This layer provides an RFNoC + REDHAWK demo platform for the Ettus Research E310 SG1.  Included are example Programmable and Persona Devices as well as custom implemenations for existing Components (that have hardware implementations via RFNoC) following a the discussion, [here](https://geontech.com/persona-device-pattern/).

 > **IMPORTANT:** One should expect that the components and devices provided in this layer are only compatible with the Ettus E310 SG1 device (`MACHINE` type `ettus-e3xx-sg1`).  However, this is _NOT_ enforced explicitly in the recipes provided by this layer.

 > **IMPORTANT:** This device does have transmitters, however, this demo does not make use of them; this demo is **receive only**.

## Example Image

The example disk image is `redhawk-usrp-uhd-rfnoc-image`.  On boot-up, it will start the following:

1. OmniNames
2. OmniEvents
3. REDHAWK_DEV Domain
4. Device Manager:
    2. RFNoC_DefaultPersona_1 - FIR and Decimate blocks available
    3. RFNoC_DefaultPersona_2 - FFT block available (configured to be magnitude)
    4. RFNoC_ProgrammableDevice - FEI Tuner control

The following hardware-accelerated Components are installed:

1. rh.TuneFilterDecimate
2. rh.psd
3. RFNoC_TestComponent

 > NOTE: The RFNoC_TestComponent allows you to read and write key-value attributes to named block IDs, serving as a model for how (1) and (2) can function as REDHAWK-facing proxies to blocks via the included RFNoC integration SoftPkg library, `RFNoC_RH`.

## Theory of Operation

The Programmable and Persona Device pattern description in the REDHAWK Documentation and its API leave considerable room for implementation choices.  The general theory of operation discussed in the reference discussion (above) is that we have _Persona_ Executable Devices that identify FPGA firmware loads containing some hardware-accelerated implementations of REDHAWK Components.  The _Programmable_ device is in charge of managing the FPGA loads and exposing the FEI (i.e., even though the USRP_UHD is installed, you do not need it running if you're using the RFNoC_ProgrammableDevice).  Because a firmware must be loaded in order to use the FEI, the steps of operation become:

1. Allocate the Persona device containing your preferred hardware load.  If successful, the parent Programmable device will indicate the state as ACTIVE in the `hw_load_statuses` list.
2. Load a waveform with the accelerated components.
3. Allocate the FEI Device (to activate the tuner) if the hardware load contains the Radio block and connect it to your waveform.

Because of the nature of the underlying tooling, the Programmable device checks the default image location to see if it exists as a link or not (`/usr/share/uhd/images/usrp_e310_fpga.bit`).  If it exists on start-up, then every hardware load request must have that same name.  If you're allocating a persona but the `hw_load_statuses` still doesn't indicate the load as active, verify that `.bit` file is unlinked and re-start the node.

## Demos

There are 2 waveforms installed, each utilizing one of the installed default personas and the FEI tuner in the programmable device.  The waveform name to persona relationships are as follows:

1. RFNoC_TFD_Waveform -> RFNoC_DefaultPersona_1
2. RFNoC_PSD_Waveform -> RFNoC_DefaultPersona_2

In order to run these, you need a combination of the Python shell and an IDE connected over the network to the E3xx REDHAWK_DEV Domain.  Using the IDE to connect to a Domain is covered in the REDHAWK documentation and many other resources.  The Python shell on the other hand is a bit more specific to this project since we need to perform an empty allocation against a persona ahead of launching the waveform.  We then must assign the waveforms component(s) to the persona supplying the block.  For example, allocating and deallocating the first persona would look like this:

```python
>>> from ossie.utils import redhawk
>>> dom = redhawk.attach()
>>> pd = dom.devices[0]; pd.instanceName
'RFNoC_DefaultPersona_1'
>>> pd.allocateCapacity([])
True
# All done?
>>> pd.deallocateCapacity([])
```

While the Persona is allocated, you should be able to launch the corresponding waveform via the IDE, assigning the component to the appropriate Persona you just allocated.
