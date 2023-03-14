// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.0;

contract Auction {

    struct Requirements {
        string vnfName;
        string vnfType;
        uint8 numCpus;
    }

    Requirements requirements;

    constructor(Requirements memory _requirements) {
        requirements = _requirements;
    }

    function getRequirements() public view returns (Requirements memory) {
        return requirements;
    }

}
